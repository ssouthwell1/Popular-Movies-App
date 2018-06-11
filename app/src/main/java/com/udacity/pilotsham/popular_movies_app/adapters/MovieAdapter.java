package com.udacity.pilotsham.popular_movies_app.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.pilotsham.popular_movies_app.R;
import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shamarisouthwell on 8/31/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> mMovies;
    private Context mContext;
    private MovieAdapterOnClickHandler mMovieAdapterOnClickHandler;

    public MovieAdapter(Context context, List<Movie> movieList, MovieAdapterOnClickHandler onClickHandler) {
        this.mMovies = movieList;
        this.mContext = context;
        mMovieAdapterOnClickHandler = onClickHandler;
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

        Movie movie = mMovies.get(position);
        holder.bindTo(movie);

    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }

        return mMovies.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_movie_poster)
        ImageView movieItemView;
        @BindView(R.id.cv_movie_item)
        CardView movieItemCardView;
        @BindView(R.id.movie_item_title)
        TextView movieItemTitle;
        @BindView(R.id.movie_item_year)
        TextView movieItemYear;
        Movie mMovie;


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

        }


        private MovieAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            // Refactor later
            view.setOnClickListener(this::onClick);
            movieItemView.setOnClickListener(this);
        }

    }

//    public void setMovieData(ArrayList<Movie> movies) {
//
//        mMovies = movies;
//        notifyDataSetChanged();
//    }


}
