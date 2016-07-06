package com.binarybricks.pragya.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.binarybricks.pragya.popularmovies.provider.favoritemovies.FavoritemoviesCursor;
import com.binarybricks.pragya.popularmovies.provider.favoritemovies.FavoritemoviesSelection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviePostersFragment extends Fragment {

    public static final String POPULARITY_DESC = "popularity.desc";
    public static final String ORIGINAL_TITLE = "ORIGINAL_TITLE";
    public static final String OVERVIEW = "OVERVIEW";
    public static final String USER_RATING = "USER_RATING";
    public static final String RELEASE_DATE = "RELEASE_DATE";
    public static final String MOVIE_POSTER = "MOVIE_POSTER";
    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String MOVIE_BACKDROP = "MOVIE_BACKDROP";
    public static final String VOTE_AVERAGE_DESC = "vote_average.desc";
    public static final String MOVIE_BASE_URI = "https://api.themoviedb.org/3/discover/movie?";
    public static final String SORT_BY = "sort_by";
    public static final String API_KEY = "api_key";
    public static final String API_KEY_VALUE = "7c7eaaf248a4275ec8122f54af59fffa";

    public MoviePostersFragment() {
    }

    GridView gridViewMovie;
    MovieGridViewAdapter movieGridViewAdapter;
    private ArrayList<MovieDetails> movieDetailsArrayList;
    private ArrayList<MovieDetails> favoriteMoviesDetailsArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_movie_posters, container, false);
        new FetchMovieData().execute(POPULARITY_DESC);
        gridViewMovie= (GridView) mainView.findViewById(R.id.moviePosterGrid);
        movieGridViewAdapter=new MovieGridViewAdapter(getActivity());
        gridViewMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent movieDetailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                movieDetailIntent.putExtra(ORIGINAL_TITLE, movieDetailsArrayList.get(position).getOriginalTitle());
                movieDetailIntent.putExtra(OVERVIEW, movieDetailsArrayList.get(position).getOverview());
                movieDetailIntent.putExtra(USER_RATING, movieDetailsArrayList.get(position).getRating());
                movieDetailIntent.putExtra(RELEASE_DATE, movieDetailsArrayList.get(position).getReleaseDate());
                movieDetailIntent.putExtra(MOVIE_POSTER, movieDetailsArrayList.get(position).getPoster());
                movieDetailIntent.putExtra(MOVIE_BACKDROP, movieDetailsArrayList.get(position).getBackdrop());
                movieDetailIntent.putExtra(MOVIE_ID, movieDetailsArrayList.get(position).getMovie_id());
                startActivity(movieDetailIntent);
            }
        });
        return mainView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_posters, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        switch (id){
            case R.id.popularity:
                new FetchMovieData().execute(POPULARITY_DESC);
                return true;
            case R.id.topRated:
                new FetchMovieData().execute(VOTE_AVERAGE_DESC);
                return true;
            case R.id.favourites:
                getFavoriteMovieDataFromDB();
                movieGridViewAdapter.setFavoriteMoviesDetailsArrayList(favoriteMoviesDetailsArrayList);
                gridViewMovie.setAdapter(movieGridViewAdapter);
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public class FetchMovieData extends AsyncTask<String, Integer,ArrayList<MovieDetails>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<MovieDetails> doInBackground(String... popularity) {
            return getMovieDataFromServer(popularity[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDetails> movieDetailsList) {
            movieDetailsArrayList=movieDetailsList;
            movieGridViewAdapter.setMovieDetailsArrayList(movieDetailsList);
            gridViewMovie.setAdapter(movieGridViewAdapter);
        }
    }

    private ArrayList<MovieDetails> getMovieDataFromServer(String popularity) {
    // These two need to be declared outside the try/catch
    // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr = null;

        try {
            Uri.Builder urlBuilder = Uri.parse(MOVIE_BASE_URI).buildUpon();
            urlBuilder.appendQueryParameter(SORT_BY, popularity);
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

            return getMovieDataFromJson(movieJsonStr);

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
    private ArrayList<MovieDetails> getMovieDataFromJson(String movieJson){
        ArrayList<MovieDetails> movieDetailsList=new ArrayList<>();
        try {
            JSONObject movieJsonObject = new JSONObject(movieJson);
            JSONArray resultArray=movieJsonObject.getJSONArray("results");
            for(int i = 0 ;i<resultArray.length();i++)
            {
                JSONObject movieData=resultArray.getJSONObject(i);
                MovieDetails movieDetails=new MovieDetails();
                movieDetails.setOriginalTitle(movieData.getString("title"));
                movieDetails.setPoster(movieData.getString("poster_path"));
                movieDetails.setBackdrop(movieData.getString("backdrop_path"));
                movieDetails.setOverview(movieData.getString("overview"));
                movieDetails.setRating(movieData.getDouble("vote_average"));
                movieDetails.setReleaseDate(movieData.getString("release_date"));
                movieDetails.setMovie_id(movieData.getString("id"));
                movieDetailsList.add(movieDetails);
            }
        }
        catch (JSONException je)
        {
         je.printStackTrace();
        }
        return movieDetailsList;
    }

    private ArrayList<MovieDetails> getFavoriteMovieDataFromDB(){

        favoriteMoviesDetailsArrayList = new ArrayList<>();
        MovieDetails favoriteMovieDetails = new MovieDetails();

        FavoritemoviesSelection favoritemoviesSelection = new FavoritemoviesSelection();
        FavoritemoviesCursor favoritemoviesCursor = favoritemoviesSelection.query(getActivity().getContentResolver());
        if(favoritemoviesCursor.moveToFirst()){
            do {
                favoriteMovieDetails.setBackdrop(favoritemoviesCursor.getBackdrop());
                favoriteMovieDetails.setPoster(favoritemoviesCursor.getPoster());
                favoriteMovieDetails.setOriginalTitle(favoritemoviesCursor.getTitle());
                favoriteMovieDetails.setOverview(favoritemoviesCursor.getOverview());
                favoriteMovieDetails.setReleaseDate(favoritemoviesCursor.getReleaseDate());
                favoriteMovieDetails.setRating(Double.valueOf(favoritemoviesCursor.getVoteAverage()));
                favoriteMovieDetails.setTrailor(favoritemoviesCursor.getTrailor1());
                favoriteMovieDetails.setReviews(favoritemoviesCursor.getReviews());
                favoriteMoviesDetailsArrayList.add(favoriteMovieDetails);

            }while(favoritemoviesCursor.moveToNext());
        }

        return favoriteMoviesDetailsArrayList;
    }

    /**
     * This Method checks if we have internet connection.
     *
     * @param ctx the context, used to fetch resources and components
     * @return true if connectivity available, false otherwise.
     * */
    public static boolean isInternetAvailable(Context ctx)
    {
        try
        {
            ConnectivityManager cm=(ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm==null)
            {
                return false;
            }
            NetworkInfo networkInfo=cm.getActiveNetworkInfo();
            return networkInfo!=null && networkInfo.isConnected();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}