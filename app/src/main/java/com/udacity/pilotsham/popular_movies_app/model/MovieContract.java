package com.udacity.pilotsham.popular_movies_app.model;

import android.provider.BaseColumns;

public class MovieContract {


    public final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "FAVORITES";
        public static final String COLUMN_MOVIE_TITLE = "TITLE";

    }


}
