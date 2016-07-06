package com.binarybricks.pragya.popularmovies;

/**
 * Created by PRAGYA on 21/03/2016.
 */
public class MovieDetails {
    String originalTitle;
    String overview;
    String poster;
    Double rating;
    String releaseDate;
    String backdrop;
    String trailor;
    String reviews;

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public String setMovie_id(String movie_id) {
        this.movie_id = movie_id;
        return movie_id;
    }

    String movie_id;

    public String getTrailor() {
        return trailor;
    }

    public void setTrailor(String trailor) {
        this.trailor = trailor;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
