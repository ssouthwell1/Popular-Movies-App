package com.udacity.pilotsham.popular_movies_app.adapters;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.pilotsham.popular_movies_app.R;
import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.model.MovieContract;
import com.udacity.pilotsham.popular_movies_app.model.MovieProvider;
import com.udacity.pilotsham.popular_movies_app.presenter.BasePresenter;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by shamarisouthwell on 8/31/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> implements BasePresenter {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private Movie mMovie;
    private ArrayList<Movie> mMovies;
    private Context mContext;
    private MovieAdapterOnClickHandler mMovieAdapterOnClickHandler;


    public MovieAdapter(Context context, ArrayList<Movie> movieList, MovieAdapterOnClickHandler onClickHandler) {
        this.mMovies = movieList;
        this.mContext = context;
        mMovieAdapterOnClickHandler = onClickHandler;
    }

    @Override
    public boolean isFavorite() {
        Cursor movieCursor = mContext.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                new String[]{MovieContract.MovieEntry.COLUMN_MOVIE_ID},
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = " + mMovie.getId(),
                null,
                null);
        if (movieCursor != null && movieCursor.moveToFirst()) {
            movieCursor.close();
            return true;
        }
        return false;
    }

    @Override
    public void addToFavorites() {
        if (!isFavorite()) {
            ContentValues cv = new ContentValues();
            cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, mMovie.getId());
            cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE, mMovie.getTitle());
            cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER_PATH, mMovie.getPosterPath());
            cv.put(MovieContract.MovieEntry.COLUMN_USER_RATING, mMovie.getVoteAverage());
            cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE, mMovie.getReleaseDate());
            mContext.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, cv);

        }
    }

    @Override
    public void removeFromFavorites() {

        if (isFavorite()) {
            mContext.getContentResolver().delete(MovieContract.MovieEntry.CONTENT_URI,
                    MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = " + mMovie.getId(), null);

        }


    }

    public interface MovieAdapterOnClickHandler {
        void onMovieClick(Movie movie);

    }


    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);

        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {

        mMovie = mMovies.get(position);
        holder.bindTo(mMovie);

    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }

        return mMovies.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Movie mMovie;
        @BindView(R.id.image_movie_poster)
        ImageView movieItemView;
        @BindView(R.id.cv_movie_item)
        CardView movieItemCardView;
        @BindView(R.id.movie_item_title)
        TextView movieItemTitle;
        @BindView(R.id.movie_item_year)
        TextView movieItemYear;
        @BindView(R.id.movie_item_favorite_btn)
        ImageButton movieFavoriteButton;
        @BindView(R.id.movie_item_favorite_btn_fill)
        ImageButton movieFavoriteButtonFill;
        boolean isFavorite = false;

        // TODO This is working for the onCLick, need to refactor

//        @OnClick(R.id.movie_item_favorite_btn)
//        public void favorite() {
//            movieFavoriteButton.setVisibility(View.GONE);
//            movieFavoriteButtonFill.setVisibility(View.VISIBLE);
//        }


        @Override
        public void onClick(View view) {

            mMovieAdapterOnClickHandler.onMovieClick(mMovie);

        }

        private void bindTo(Movie movie) {
            mMovie = movie;
            movieItemTitle.setText(movie.getTitle());
            movieItemYear.setText(movie.getReleaseDate());
            movieItemYear.setText(movie.getReleaseDate());
            Uri uri = Uri.parse(StringUtils.BASE_POSTER_IMAGE_URL).buildUpon().appendEncodedPath(StringUtils.DEFAULT_MOVIE_POSTER_SIZE)
                    .appendEncodedPath(movie.getPosterPath())
                    .build();

            Log.d(TAG, "Image url: " + uri.toString());

            Picasso.with(mContext).load(uri).into(movieItemView);
            Picasso.with(mContext).setLoggingEnabled(true);
            setFavoriteButtons();


        }

        private void setFavoriteButtons() {
            if (isFavorite) {
                movieFavoriteButton.setVisibility(View.GONE);
                movieFavoriteButtonFill.setVisibility(View.VISIBLE);
            } else {
                movieFavoriteButton.setVisibility(View.VISIBLE);
                movieFavoriteButtonFill.setVisibility(View.GONE);
            }
        }

        private MovieAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            // Refactor later
            /**
             * This is taking the entire card view and not seperating the movie favorite button from it
             */
            view.setOnClickListener(this::onClick);
            movieItemView.setOnClickListener(this);
        }

    }

//    public void setMovieData(ArrayArrayList<Movie> movies) {
//
//        mMovies = movies;
//        notifyDataSetChanged();
//    }

    public void add(Cursor cursor) {
        mMovies.clear();
        if (cursor != null && cursor.moveToFirst()) {

        }

    }

}
