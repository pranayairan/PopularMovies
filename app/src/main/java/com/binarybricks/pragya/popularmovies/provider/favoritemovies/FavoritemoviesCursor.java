package com.binarybricks.pragya.popularmovies.provider.favoritemovies;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.binarybricks.pragya.popularmovies.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code favoritemovies} table.
 */
public class FavoritemoviesCursor extends AbstractCursor implements FavoritemoviesModel {
    public FavoritemoviesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(FavoritemoviesColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * ID of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieid() {
        String res = getStringOrNull(FavoritemoviesColumns.MOVIEID);
        return res;
    }

    /**
     * Title of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(FavoritemoviesColumns.TITLE);
        return res;
    }

    /**
     * Poster of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getPoster() {
        String res = getStringOrNull(FavoritemoviesColumns.POSTER);
        return res;
    }

    /**
     * Cover pic of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getBackdrop() {
        String res = getStringOrNull(FavoritemoviesColumns.BACKDROP);
        return res;
    }

    /**
     * Synopsis of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getOverview() {
        String res = getStringOrNull(FavoritemoviesColumns.OVERVIEW);
        return res;
    }

    /**
     * user rating of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getVoteAverage() {
        String res = getStringOrNull(FavoritemoviesColumns.VOTE_AVERAGE);
        return res;
    }

    /**
     * Release Date of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getReleaseDate() {
        String res = getStringOrNull(FavoritemoviesColumns.RELEASE_DATE);
        return res;
    }

    /**
     *  first Trailor of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getTrailor1() {
        String res = getStringOrNull(FavoritemoviesColumns.TRAILOR1);
        return res;
    }

    /**
     * second trailor of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getTrailor2() {
        String res = getStringOrNull(FavoritemoviesColumns.TRAILOR2);
        return res;
    }

    /**
     * Reviews of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getReviews() {
        String res = getStringOrNull(FavoritemoviesColumns.REVIEWS);
        return res;
    }
}
