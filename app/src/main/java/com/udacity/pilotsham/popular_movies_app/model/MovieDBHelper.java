package com.udacity.pilotsham.popular_movies_app.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies.db";

    private static final int DATABASE_VERSION = 1;

    private static MovieDBHelper movieDBHelperInstance;


    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY," +
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL," +
                MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW + " TEXT NOT NULL," +
                MovieContract.MovieEntry.COLUMN_MOVIE_POSTER_PATH + " TEXT NOT NULL," +
                MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL," +
                MovieContract.MovieEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL," +
                MovieContract.MovieEntry.COLUMN_CURRENT_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                MovieContract.MovieEntry.COLUMN_USER_RATING + " TEXT NOT NULL" +
                ");";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }


//    public static synchronized MovieDBHelper getInstance() {
//            if (movieDBHelperInstance == null) {
//                movieDBHelperInstance = new MovieDBHelper(this);
//            }
}



