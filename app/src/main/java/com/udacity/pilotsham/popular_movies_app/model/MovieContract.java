package com.udacity.pilotsham.popular_movies_app.model;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {

    public static final String CONTENT_AUTHORITY = "com.udacity.pilotsham.popular_movies_app";
    public static final String CONTENT_PROVIDER_PREFIX = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_PROVIDER_PREFIX + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "movie";


    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        public static final String TABLE_NAME = "FAVORITES";
        public static final String COLUMN_MOVIE_TITLE = "title";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_POSTER_PATH = "poster_path";
        public static final String COLUMN_MOVIE_OVERVIEW = "overview";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "release_date";
        public static final String COLUMN_CURRENT_TIME = "time_updated";
        public static final String COLUMN_USER_RATING = "user_rating";
        

    }


}
