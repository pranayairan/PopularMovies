package com.binarybricks.pragya.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.binarybricks.pragya.popularmovies.provider.favoritemovies.FavoritemoviesContentValues;
import com.binarybricks.pragya.popularmovies.provider.favoritemovies.FavoritemoviesCursor;
import com.binarybricks.pragya.popularmovies.provider.favoritemovies.FavoritemoviesSelection;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String MOVIE_DETAIL_BASE_URL = "http://api.themoviedb.org/3/movie/";
    public static final String API_KEY = "api_key";
    public static final String API_KEY_VALUE = "7c7eaaf248a4275ec8122f54af59fffa";

    TextView titleTextView;
    TextView userRatingTextView;
    TextView releaseDateTextView;
    TextView overviewTextView;
    ImageView posterImageView;
    ImageView backdropImageView;
    LinearLayout trailorView1;
    LinearLayout trailorView2;
    TextView reviewsTextView;
    ToggleButton starButtonView;

    String displayMovieTitle;
    String displayMovieOverview;
    String displayMovieReleaseDate;
    Double displayMovieUserRating;
    String displayMoviePoster;
    String displayMovieBackdrop;
    String displayMovieTrailor1;
    String displayMovieTrailor2;
    String displayMovieReviews;

    String getMovieIdFromIntent;

    public static final String MOVIE_TRAILOR1 = "MOVIE_TRAILOR1";
    public static final String MOVIE_TRAILOR2 = "MOVIE_TRAILOR2";
    public static final String REVIEWS = "REVIEWS";

    private ArrayList<String> movieTrailorsArrayList;

    public MovieDetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent showMovieDetailIntent = getActivity().getIntent();

        View movieDetailView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        titleTextView = (TextView) movieDetailView.findViewById(R.id.movieTitle);
        displayMovieTitle = showMovieDetailIntent.getStringExtra(MoviePostersFragment.ORIGINAL_TITLE);
        titleTextView.setText(displayMovieTitle);

        overviewTextView = (TextView) movieDetailView.findViewById(R.id.overview);
        displayMovieOverview = showMovieDetailIntent.getStringExtra(MoviePostersFragment.OVERVIEW);
        overviewTextView.setText(displayMovieOverview);

        releaseDateTextView = (TextView) movieDetailView.findViewById(R.id.releaseDate);
        displayMovieReleaseDate = showMovieDetailIntent.getStringExtra(MoviePostersFragment.RELEASE_DATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(displayMovieReleaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM dd,yyyy");
        releaseDateTextView.setText(newFormat.format(date));

        userRatingTextView = (TextView) movieDetailView.findViewById(R.id.userRating);
        displayMovieUserRating = showMovieDetailIntent.getDoubleExtra(MoviePostersFragment.USER_RATING, 0);
        userRatingTextView.setText(displayMovieUserRating + "/10");
        float rating = Float.parseFloat(String.valueOf(displayMovieUserRating));
        RatingBar rb = (RatingBar) movieDetailView.findViewById(R.id.ratingBar);
        rb.setRating(rating);

        posterImageView = (ImageView) movieDetailView.findViewById(R.id.moviePoster);
        displayMoviePoster = showMovieDetailIntent.getStringExtra(MoviePostersFragment.MOVIE_POSTER);
        Picasso.with(getActivity()).load(IMAGE_BASE_URL + "w342" + displayMoviePoster).into(posterImageView);

        backdropImageView = (ImageView) movieDetailView.findViewById(R.id.movieBackdrop);
        displayMovieBackdrop = showMovieDetailIntent.getStringExtra(MoviePostersFragment.MOVIE_BACKDROP);
        Picasso.with(getActivity()).load(IMAGE_BASE_URL + "w780" + displayMovieBackdrop).into(backdropImageView);

        reviewsTextView = (TextView) movieDetailView.findViewById(R.id.review);
        getMovieIdFromIntent = showMovieDetailIntent.getStringExtra(MoviePostersFragment.MOVIE_ID);
        new FetchMovieReviews().execute(getMovieIdFromIntent);

        new FetchMovieTrailors().execute(getMovieIdFromIntent);
        trailorView1 = (LinearLayout) movieDetailView.findViewById(R.id.trailor1);
        trailorView2 = (LinearLayout) movieDetailView.findViewById(R.id.trailor2);

        trailorView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieTrailorsArrayList !=null && !movieTrailorsArrayList.isEmpty()) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + movieTrailorsArrayList.get(0))));
                }
            }
        });

        trailorView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieTrailorsArrayList !=null && movieTrailorsArrayList.size()>1) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + movieTrailorsArrayList.get(1))));
                }
            }
        });
        starButtonView = (ToggleButton) movieDetailView.findViewById(R.id.addToFavourites);
        starButtonView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    addFavoriteMoviesToDB();
                }
                else{
                // remove from db

                }
            }
        });

        if (isMovieInFavorite(getMovieIdFromIntent)){
            //set movie as favorite
            starButtonView.setChecked(true);
        }

        return movieDetailView;
    }

    private String getReviewDataFromServer(String id) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr = null;
        String movieDetailUrl = MOVIE_DETAIL_BASE_URL + id + "/reviews";

        try {
            Uri.Builder urlBuilder = Uri.parse(movieDetailUrl).buildUpon();
            urlBuilder.appendQueryParameter(API_KEY, API_KEY_VALUE);

            URL url = new URL(urlBuilder.build().toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                movieJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                movieJsonStr = null;
            }
            movieJsonStr = buffer.toString();

            return getMovieReviewDataFromJson(movieJsonStr);

        } catch (IOException e) {
            movieJsonStr = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getMovieReviewDataFromJson(String movieReviewJson) {
        String movieReview = "";
        try {
            JSONObject movieReviewJsonObject = new JSONObject(movieReviewJson);
            JSONArray movieReviewJsonArray = movieReviewJsonObject.getJSONArray("results");
            if (movieReviewJsonArray.length() > 0) {
                movieReview = movieReviewJsonArray.getJSONObject(0).getString("content");
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return movieReview;
    }

    public class FetchMovieReviews extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... id) {
            return getReviewDataFromServer(id[0]);
        }

        @Override
        protected void onPostExecute(String movieReviews) {

            reviewsTextView.setText(movieReviews);
            displayMovieReviews=movieReviews;
        }
    }

    private ArrayList<String> getMovieTrailorFromServer(String id) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr = null;
        String movieTrailorUrl = MOVIE_DETAIL_BASE_URL + id + "/videos";

        try {
            Uri.Builder urlBuilder = Uri.parse(movieTrailorUrl).buildUpon();
            urlBuilder.appendQueryParameter(API_KEY, API_KEY_VALUE);

            URL url = new URL(urlBuilder.build().toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                movieJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                movieJsonStr = null;
            }
            movieJsonStr = buffer.toString();

            return getMovieTrailorFromJson(movieJsonStr);

        } catch (IOException e) {
            movieJsonStr = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private ArrayList<String> getMovieTrailorFromJson(String movieTrailorJson) {
        ArrayList<String> movieTrailorsList = new ArrayList<>();
        try {
            JSONObject movieJsonObject = new JSONObject(movieTrailorJson);
            JSONArray resultArray = movieJsonObject.getJSONArray("results");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject movieTrailor = resultArray.getJSONObject(i);
                movieTrailorsList.add(movieTrailor.getString("key"));
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return movieTrailorsList;
    }

    public class FetchMovieTrailors extends AsyncTask<String, Integer, ArrayList<String>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<String> doInBackground(String... id) {
            return getMovieTrailorFromServer(id[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<String> movieTrailorsList) {
            movieTrailorsArrayList = movieTrailorsList;
        }
    }

    private void addFavoriteMoviesToDB(){

        FavoritemoviesContentValues favoritemoviesContentValues = new FavoritemoviesContentValues();
        favoritemoviesContentValues.putMovieid(getMovieIdFromIntent);
        favoritemoviesContentValues.putBackdrop(displayMovieBackdrop);
        favoritemoviesContentValues.putOverview(displayMovieOverview);
        favoritemoviesContentValues.putPoster(displayMoviePoster);
        favoritemoviesContentValues.putTitle(displayMovieTitle);
        favoritemoviesContentValues.putReleaseDate(displayMovieReleaseDate);
        favoritemoviesContentValues.putVoteAverage(displayMovieUserRating + "");
        favoritemoviesContentValues.putReviews(displayMovieReviews);
        if (movieTrailorsArrayList != null && movieTrailorsArrayList.size()>0) {
            favoritemoviesContentValues.putTrailor1(movieTrailorsArrayList.get(0));
        }
        if (movieTrailorsArrayList != null && movieTrailorsArrayList.size()>1) {
            favoritemoviesContentValues.putTrailor2(movieTrailorsArrayList.get(1));
        }

        favoritemoviesContentValues.insert(getActivity().getContentResolver());
    }
    private boolean isMovieInFavorite(String movieID) {
        FavoritemoviesSelection favoritemoviesSelection = new FavoritemoviesSelection();
        favoritemoviesSelection.movieid(movieID);
        FavoritemoviesCursor favoritemoviesCursor = favoritemoviesSelection.query(getActivity().getContentResolver());
        if (!favoritemoviesCursor.moveToFirst() || favoritemoviesCursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
