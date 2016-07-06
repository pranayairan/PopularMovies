package com.binarybricks.pragya.popularmovies.provider.favoritemovies;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.binarybricks.pragya.popularmovies.provider.base.AbstractSelection;

/**
 * Selection for the {@code favoritemovies} table.
 */
public class FavoritemoviesSelection extends AbstractSelection<FavoritemoviesSelection> {
    @Override
    protected Uri baseUri() {
        return FavoritemoviesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoritemoviesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoritemoviesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoritemoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoritemoviesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoritemoviesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoritemoviesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoritemoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoritemoviesCursor query(Context context) {
        return query(context, null);
    }


    public FavoritemoviesSelection id(long... value) {
        addEquals("favoritemovies." + FavoritemoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoritemoviesSelection idNot(long... value) {
        addNotEquals("favoritemovies." + FavoritemoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoritemoviesSelection orderById(boolean desc) {
        orderBy("favoritemovies." + FavoritemoviesColumns._ID, desc);
        return this;
    }

    public FavoritemoviesSelection orderById() {
        return orderById(false);
    }

    public FavoritemoviesSelection movieid(String... value) {
        addEquals(FavoritemoviesColumns.MOVIEID, value);
        return this;
    }

    public FavoritemoviesSelection movieidNot(String... value) {
        addNotEquals(FavoritemoviesColumns.MOVIEID, value);
        return this;
    }

    public FavoritemoviesSelection movieidLike(String... value) {
        addLike(FavoritemoviesColumns.MOVIEID, value);
        return this;
    }

    public FavoritemoviesSelection movieidContains(String... value) {
        addContains(FavoritemoviesColumns.MOVIEID, value);
        return this;
    }

    public FavoritemoviesSelection movieidStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.MOVIEID, value);
        return this;
    }

    public FavoritemoviesSelection movieidEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.MOVIEID, value);
        return this;
    }

    public FavoritemoviesSelection orderByMovieid(boolean desc) {
        orderBy(FavoritemoviesColumns.MOVIEID, desc);
        return this;
    }

    public FavoritemoviesSelection orderByMovieid() {
        orderBy(FavoritemoviesColumns.MOVIEID, false);
        return this;
    }

    public FavoritemoviesSelection title(String... value) {
        addEquals(FavoritemoviesColumns.TITLE, value);
        return this;
    }

    public FavoritemoviesSelection titleNot(String... value) {
        addNotEquals(FavoritemoviesColumns.TITLE, value);
        return this;
    }

    public FavoritemoviesSelection titleLike(String... value) {
        addLike(FavoritemoviesColumns.TITLE, value);
        return this;
    }

    public FavoritemoviesSelection titleContains(String... value) {
        addContains(FavoritemoviesColumns.TITLE, value);
        return this;
    }

    public FavoritemoviesSelection titleStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.TITLE, value);
        return this;
    }

    public FavoritemoviesSelection titleEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.TITLE, value);
        return this;
    }

    public FavoritemoviesSelection orderByTitle(boolean desc) {
        orderBy(FavoritemoviesColumns.TITLE, desc);
        return this;
    }

    public FavoritemoviesSelection orderByTitle() {
        orderBy(FavoritemoviesColumns.TITLE, false);
        return this;
    }

    public FavoritemoviesSelection poster(String... value) {
        addEquals(FavoritemoviesColumns.POSTER, value);
        return this;
    }

    public FavoritemoviesSelection posterNot(String... value) {
        addNotEquals(FavoritemoviesColumns.POSTER, value);
        return this;
    }

    public FavoritemoviesSelection posterLike(String... value) {
        addLike(FavoritemoviesColumns.POSTER, value);
        return this;
    }

    public FavoritemoviesSelection posterContains(String... value) {
        addContains(FavoritemoviesColumns.POSTER, value);
        return this;
    }

    public FavoritemoviesSelection posterStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.POSTER, value);
        return this;
    }

    public FavoritemoviesSelection posterEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.POSTER, value);
        return this;
    }

    public FavoritemoviesSelection orderByPoster(boolean desc) {
        orderBy(FavoritemoviesColumns.POSTER, desc);
        return this;
    }

    public FavoritemoviesSelection orderByPoster() {
        orderBy(FavoritemoviesColumns.POSTER, false);
        return this;
    }

    public FavoritemoviesSelection backdrop(String... value) {
        addEquals(FavoritemoviesColumns.BACKDROP, value);
        return this;
    }

    public FavoritemoviesSelection backdropNot(String... value) {
        addNotEquals(FavoritemoviesColumns.BACKDROP, value);
        return this;
    }

    public FavoritemoviesSelection backdropLike(String... value) {
        addLike(FavoritemoviesColumns.BACKDROP, value);
        return this;
    }

    public FavoritemoviesSelection backdropContains(String... value) {
        addContains(FavoritemoviesColumns.BACKDROP, value);
        return this;
    }

    public FavoritemoviesSelection backdropStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.BACKDROP, value);
        return this;
    }

    public FavoritemoviesSelection backdropEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.BACKDROP, value);
        return this;
    }

    public FavoritemoviesSelection orderByBackdrop(boolean desc) {
        orderBy(FavoritemoviesColumns.BACKDROP, desc);
        return this;
    }

    public FavoritemoviesSelection orderByBackdrop() {
        orderBy(FavoritemoviesColumns.BACKDROP, false);
        return this;
    }

    public FavoritemoviesSelection overview(String... value) {
        addEquals(FavoritemoviesColumns.OVERVIEW, value);
        return this;
    }

    public FavoritemoviesSelection overviewNot(String... value) {
        addNotEquals(FavoritemoviesColumns.OVERVIEW, value);
        return this;
    }

    public FavoritemoviesSelection overviewLike(String... value) {
        addLike(FavoritemoviesColumns.OVERVIEW, value);
        return this;
    }

    public FavoritemoviesSelection overviewContains(String... value) {
        addContains(FavoritemoviesColumns.OVERVIEW, value);
        return this;
    }

    public FavoritemoviesSelection overviewStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.OVERVIEW, value);
        return this;
    }

    public FavoritemoviesSelection overviewEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.OVERVIEW, value);
        return this;
    }

    public FavoritemoviesSelection orderByOverview(boolean desc) {
        orderBy(FavoritemoviesColumns.OVERVIEW, desc);
        return this;
    }

    public FavoritemoviesSelection orderByOverview() {
        orderBy(FavoritemoviesColumns.OVERVIEW, false);
        return this;
    }

    public FavoritemoviesSelection voteAverage(String... value) {
        addEquals(FavoritemoviesColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoritemoviesSelection voteAverageNot(String... value) {
        addNotEquals(FavoritemoviesColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoritemoviesSelection voteAverageLike(String... value) {
        addLike(FavoritemoviesColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoritemoviesSelection voteAverageContains(String... value) {
        addContains(FavoritemoviesColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoritemoviesSelection voteAverageStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoritemoviesSelection voteAverageEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoritemoviesSelection orderByVoteAverage(boolean desc) {
        orderBy(FavoritemoviesColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public FavoritemoviesSelection orderByVoteAverage() {
        orderBy(FavoritemoviesColumns.VOTE_AVERAGE, false);
        return this;
    }

    public FavoritemoviesSelection releaseDate(String... value) {
        addEquals(FavoritemoviesColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoritemoviesSelection releaseDateNot(String... value) {
        addNotEquals(FavoritemoviesColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoritemoviesSelection releaseDateLike(String... value) {
        addLike(FavoritemoviesColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoritemoviesSelection releaseDateContains(String... value) {
        addContains(FavoritemoviesColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoritemoviesSelection releaseDateStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoritemoviesSelection releaseDateEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoritemoviesSelection orderByReleaseDate(boolean desc) {
        orderBy(FavoritemoviesColumns.RELEASE_DATE, desc);
        return this;
    }

    public FavoritemoviesSelection orderByReleaseDate() {
        orderBy(FavoritemoviesColumns.RELEASE_DATE, false);
        return this;
    }

    public FavoritemoviesSelection trailor1(String... value) {
        addEquals(FavoritemoviesColumns.TRAILOR1, value);
        return this;
    }

    public FavoritemoviesSelection trailor1Not(String... value) {
        addNotEquals(FavoritemoviesColumns.TRAILOR1, value);
        return this;
    }

    public FavoritemoviesSelection trailor1Like(String... value) {
        addLike(FavoritemoviesColumns.TRAILOR1, value);
        return this;
    }

    public FavoritemoviesSelection trailor1Contains(String... value) {
        addContains(FavoritemoviesColumns.TRAILOR1, value);
        return this;
    }

    public FavoritemoviesSelection trailor1StartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.TRAILOR1, value);
        return this;
    }

    public FavoritemoviesSelection trailor1EndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.TRAILOR1, value);
        return this;
    }

    public FavoritemoviesSelection orderByTrailor1(boolean desc) {
        orderBy(FavoritemoviesColumns.TRAILOR1, desc);
        return this;
    }

    public FavoritemoviesSelection orderByTrailor1() {
        orderBy(FavoritemoviesColumns.TRAILOR1, false);
        return this;
    }

    public FavoritemoviesSelection trailor2(String... value) {
        addEquals(FavoritemoviesColumns.TRAILOR2, value);
        return this;
    }

    public FavoritemoviesSelection trailor2Not(String... value) {
        addNotEquals(FavoritemoviesColumns.TRAILOR2, value);
        return this;
    }

    public FavoritemoviesSelection trailor2Like(String... value) {
        addLike(FavoritemoviesColumns.TRAILOR2, value);
        return this;
    }

    public FavoritemoviesSelection trailor2Contains(String... value) {
        addContains(FavoritemoviesColumns.TRAILOR2, value);
        return this;
    }

    public FavoritemoviesSelection trailor2StartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.TRAILOR2, value);
        return this;
    }

    public FavoritemoviesSelection trailor2EndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.TRAILOR2, value);
        return this;
    }

    public FavoritemoviesSelection orderByTrailor2(boolean desc) {
        orderBy(FavoritemoviesColumns.TRAILOR2, desc);
        return this;
    }

    public FavoritemoviesSelection orderByTrailor2() {
        orderBy(FavoritemoviesColumns.TRAILOR2, false);
        return this;
    }

    public FavoritemoviesSelection reviews(String... value) {
        addEquals(FavoritemoviesColumns.REVIEWS, value);
        return this;
    }

    public FavoritemoviesSelection reviewsNot(String... value) {
        addNotEquals(FavoritemoviesColumns.REVIEWS, value);
        return this;
    }

    public FavoritemoviesSelection reviewsLike(String... value) {
        addLike(FavoritemoviesColumns.REVIEWS, value);
        return this;
    }

    public FavoritemoviesSelection reviewsContains(String... value) {
        addContains(FavoritemoviesColumns.REVIEWS, value);
        return this;
    }

    public FavoritemoviesSelection reviewsStartsWith(String... value) {
        addStartsWith(FavoritemoviesColumns.REVIEWS, value);
        return this;
    }

    public FavoritemoviesSelection reviewsEndsWith(String... value) {
        addEndsWith(FavoritemoviesColumns.REVIEWS, value);
        return this;
    }

    public FavoritemoviesSelection orderByReviews(boolean desc) {
        orderBy(FavoritemoviesColumns.REVIEWS, desc);
        return this;
    }

    public FavoritemoviesSelection orderByReviews() {
        orderBy(FavoritemoviesColumns.REVIEWS, false);
        return this;
    }
}
