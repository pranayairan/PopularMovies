package com.binarybricks.pragya.popularmovies.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.binarybricks.pragya.popularmovies.BuildConfig;
import com.binarybricks.pragya.popularmovies.provider.favoritemovies.FavoritemoviesColumns;

public class SQLOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = SQLOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLOpenHelper sInstance;
    private final Context mContext;
    private final SQLOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_FAVORITEMOVIES = "CREATE TABLE IF NOT EXISTS "
            + FavoritemoviesColumns.TABLE_NAME + " ( "
            + FavoritemoviesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FavoritemoviesColumns.MOVIEID + " TEXT, "
            + FavoritemoviesColumns.TITLE + " TEXT, "
            + FavoritemoviesColumns.POSTER + " TEXT, "
            + FavoritemoviesColumns.BACKDROP + " TEXT, "
            + FavoritemoviesColumns.OVERVIEW + " TEXT, "
            + FavoritemoviesColumns.VOTE_AVERAGE + " TEXT, "
            + FavoritemoviesColumns.RELEASE_DATE + " TEXT, "
            + FavoritemoviesColumns.TRAILOR1 + " TEXT, "
            + FavoritemoviesColumns.TRAILOR2 + " TEXT, "
            + FavoritemoviesColumns.REVIEWS + " TEXT "
            + " );";

    // @formatter:on

    public static SQLOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static SQLOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static SQLOpenHelper newInstancePreHoneycomb(Context context) {
        return new SQLOpenHelper(context);
    }

    private SQLOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new SQLOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static SQLOpenHelper newInstancePostHoneycomb(Context context) {
        return new SQLOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private SQLOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new SQLOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_FAVORITEMOVIES);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
