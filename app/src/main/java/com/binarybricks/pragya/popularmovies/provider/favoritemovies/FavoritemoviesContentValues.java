package com.binarybricks.pragya.popularmovies.provider.favoritemovies;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.binarybricks.pragya.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code favoritemovies} table.
 */
public class FavoritemoviesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return FavoritemoviesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable FavoritemoviesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable FavoritemoviesSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * ID of the movie
     */
    public FavoritemoviesContentValues putMovieid(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.MOVIEID, value);
        return this;
    }

    public FavoritemoviesContentValues putMovieidNull() {
        mContentValues.putNull(FavoritemoviesColumns.MOVIEID);
        return this;
    }

    /**
     * Title of the movie
     */
    public FavoritemoviesContentValues putTitle(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.TITLE, value);
        return this;
    }

    public FavoritemoviesContentValues putTitleNull() {
        mContentValues.putNull(FavoritemoviesColumns.TITLE);
        return this;
    }

    /**
     * Poster of the movie
     */
    public FavoritemoviesContentValues putPoster(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.POSTER, value);
        return this;
    }

    public FavoritemoviesContentValues putPosterNull() {
        mContentValues.putNull(FavoritemoviesColumns.POSTER);
        return this;
    }

    /**
     * Cover pic of the movie
     */
    public FavoritemoviesContentValues putBackdrop(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.BACKDROP, value);
        return this;
    }

    public FavoritemoviesContentValues putBackdropNull() {
        mContentValues.putNull(FavoritemoviesColumns.BACKDROP);
        return this;
    }

    /**
     * Synopsis of the movie
     */
    public FavoritemoviesContentValues putOverview(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.OVERVIEW, value);
        return this;
    }

    public FavoritemoviesContentValues putOverviewNull() {
        mContentValues.putNull(FavoritemoviesColumns.OVERVIEW);
        return this;
    }

    /**
     * user rating of the movie
     */
    public FavoritemoviesContentValues putVoteAverage(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoritemoviesContentValues putVoteAverageNull() {
        mContentValues.putNull(FavoritemoviesColumns.VOTE_AVERAGE);
        return this;
    }

    /**
     * Release Date of the movie
     */
    public FavoritemoviesContentValues putReleaseDate(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoritemoviesContentValues putReleaseDateNull() {
        mContentValues.putNull(FavoritemoviesColumns.RELEASE_DATE);
        return this;
    }

    /**
     *  first Trailor of the movie
     */
    public FavoritemoviesContentValues putTrailor1(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.TRAILOR1, value);
        return this;
    }

    public FavoritemoviesContentValues putTrailor1Null() {
        mContentValues.putNull(FavoritemoviesColumns.TRAILOR1);
        return this;
    }

    /**
     * second trailor of the movie
     */
    public FavoritemoviesContentValues putTrailor2(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.TRAILOR2, value);
        return this;
    }

    public FavoritemoviesContentValues putTrailor2Null() {
        mContentValues.putNull(FavoritemoviesColumns.TRAILOR2);
        return this;
    }

    /**
     * Reviews of the movie
     */
    public FavoritemoviesContentValues putReviews(@Nullable String value) {
        mContentValues.put(FavoritemoviesColumns.REVIEWS, value);
        return this;
    }

    public FavoritemoviesContentValues putReviewsNull() {
        mContentValues.putNull(FavoritemoviesColumns.REVIEWS);
        return this;
    }
}
