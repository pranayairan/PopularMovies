package com.binarybricks.pragya.popularmovies.provider.favoritemovies;

import android.net.Uri;
import android.provider.BaseColumns;

import com.binarybricks.pragya.popularmovies.provider.MoviesFavoriteProvider;

/**
 * This table will be storing user's favorite movies
 */
public class FavoritemoviesColumns implements BaseColumns {
    public static final String TABLE_NAME = "favoritemovies";
    public static final Uri CONTENT_URI = Uri.parse(MoviesFavoriteProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * ID of the movie
     */
    public static final String MOVIEID = "movieId";

    /**
     * Title of the movie
     */
    public static final String TITLE = "title";

    /**
     * Poster of the movie
     */
    public static final String POSTER = "poster";

    /**
     * Cover pic of the movie
     */
    public static final String BACKDROP = "backdrop";

    /**
     * Synopsis of the movie
     */
    public static final String OVERVIEW = "overview";

    /**
     * user rating of the movie
     */
    public static final String VOTE_AVERAGE = "vote_average";

    /**
     * Release Date of the movie
     */
    public static final String RELEASE_DATE = "release_date";

    /**
     *  first Trailor of the movie
     */
    public static final String TRAILOR1 = "trailor1";

    /**
     * second trailor of the movie
     */
    public static final String TRAILOR2 = "trailor2";

    /**
     * Reviews of the movie
     */
    public static final String REVIEWS = "reviews";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIEID,
            TITLE,
            POSTER,
            BACKDROP,
            OVERVIEW,
            VOTE_AVERAGE,
            RELEASE_DATE,
            TRAILOR1,
            TRAILOR2,
            REVIEWS
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIEID) || c.contains("." + MOVIEID)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(POSTER) || c.contains("." + POSTER)) return true;
            if (c.equals(BACKDROP) || c.contains("." + BACKDROP)) return true;
            if (c.equals(OVERVIEW) || c.contains("." + OVERVIEW)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("." + VOTE_AVERAGE)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("." + RELEASE_DATE)) return true;
            if (c.equals(TRAILOR1) || c.contains("." + TRAILOR1)) return true;
            if (c.equals(TRAILOR2) || c.contains("." + TRAILOR2)) return true;
            if (c.equals(REVIEWS) || c.contains("." + REVIEWS)) return true;
        }
        return false;
    }

}
