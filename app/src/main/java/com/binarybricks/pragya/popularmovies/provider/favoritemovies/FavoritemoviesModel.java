package com.binarybricks.pragya.popularmovies.provider.favoritemovies;

import com.binarybricks.pragya.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This table will be storing user's favorite movies
 */
public interface FavoritemoviesModel extends BaseModel {

    /**
     * ID of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getMovieid();

    /**
     * Title of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Poster of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getPoster();

    /**
     * Cover pic of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getBackdrop();

    /**
     * Synopsis of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getOverview();

    /**
     * user rating of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getVoteAverage();

    /**
     * Release Date of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getReleaseDate();

    /**
     *  first Trailor of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getTrailor1();

    /**
     * second trailor of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getTrailor2();

    /**
     * Reviews of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getReviews();
}
