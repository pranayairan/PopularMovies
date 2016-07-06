package com.binarybricks.pragya.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieGridViewAdapter extends BaseAdapter {
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342";
    private Context mContext;
        private LayoutInflater layoutInflater;
        private ArrayList<MovieDetails> movieDetailsArrayList;
        private ArrayList<MovieDetails> favoriteMoviesDetailsArrayList;

        public MovieGridViewAdapter(Context c) {
            mContext = c;
            layoutInflater=LayoutInflater.from(c);
        }

    public void setMovieDetailsArrayList(ArrayList<MovieDetails> movieDetailsArrayList) {
        this.movieDetailsArrayList = movieDetailsArrayList;
    }

    public void setFavoriteMoviesDetailsArrayList(ArrayList<MovieDetails> favoriteMoviesDetailsArrayList) {
        this.favoriteMoviesDetailsArrayList = favoriteMoviesDetailsArrayList;
    }

    public int getCount() {
            return movieDetailsArrayList.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {

            View movieGridItemView= layoutInflater.inflate(R.layout.movie_poster,parent,false);
            ImageView moviePosterImageView= (ImageView) movieGridItemView.findViewById(R.id.movie_poster);

            Picasso.with(mContext).load(IMAGE_BASE_URL +movieDetailsArrayList.get(position).getPoster()).into(moviePosterImageView);

            return movieGridItemView;
        }
}
