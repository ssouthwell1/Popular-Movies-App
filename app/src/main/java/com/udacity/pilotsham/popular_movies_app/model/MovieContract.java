package com.udacity.pilotsham.popular_movies_app.model;

import android.provider.BaseColumns;

public class MovieContract {


    public final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "FAVORITES";
        public static final String COLUMN_MOVIE_TITLE = "title";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_POSTER_PATH = "poster_path";
        public static final String COLUMN_MOVIE_OVERVIEW = "overview";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "release_date";
        public static final String COLUMN_CURRENT_TIME = "time_updated";

    }


}
