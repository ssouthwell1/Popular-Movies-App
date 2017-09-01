package com.udacity.pilotsham.popular_movies_app;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shamarisouthwell on 8/31/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.mMovies = movieList;
        this.mContext = context;

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
        Uri uri = Uri.parse(StringUtils.BASE_POSTER_IMAGE_URL).buildUpon()
                .appendEncodedPath(movie.getPosterPath())
                .build();

        Log.d(TAG, "Image url: " + uri.toString());

        Picasso.with(mContext).load(uri).centerInside().resize(500, 800).into(holder.movieItemView);
        Picasso.with(mContext).setLoggingEnabled(true);


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

        @Override
        public void onClick(View view) {

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
