package com.udacity.pilotsham.popular_movies_app.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.pilotsham.popular_movies_app.R;
import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
        ImageView movieItemView;
        Movie mMovie;

        @Override
        public void onClick(View view) {

            mMovieAdapterOnClickHandler.onMovieClick(mMovie);
        }

        public void bindTo(Movie movie) {
            mMovie = movie;
            Uri uri = Uri.parse(StringUtils.BASE_POSTER_IMAGE_URL).buildUpon().appendEncodedPath(StringUtils.DEFAULT_MOVIE_POSTER_SIZE)
                    .appendEncodedPath(movie.getPosterPath())
                    .build();

            Log.d(TAG, "Image url: " + uri.toString());

            Picasso.with(mContext).load(uri).into(movieItemView);
            Picasso.with(mContext).setLoggingEnabled(true);

        }


        public MovieAdapterViewHolder(View view) {
            super(view);

            movieItemView = (ImageView) itemView.findViewById(R.id.image_movie_poster);
            movieItemView.setOnClickListener(this);
        }

    }

    public void setMovieData(ArrayList<Movie> movies) {

        mMovies = movies;
        notifyDataSetChanged();
    }


}
