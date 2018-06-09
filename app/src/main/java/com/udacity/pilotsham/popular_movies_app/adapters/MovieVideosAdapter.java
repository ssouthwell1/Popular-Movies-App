package com.udacity.pilotsham.popular_movies_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.pilotsham.popular_movies_app.model.Video;

import java.util.List;

public class MovieVideosAdapter extends RecyclerView.Adapter<MovieVideosAdapter.MovieVideosAdapterViewHolder> {
    private List<Video> mVideos;
    private Context mContext;
    private MovieVideosOnClickHandler mMovieVideosOnClickHandler;

    @NonNull
    @Override
    public MovieVideosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVideosAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mVideos != null) {
            return mVideos.size();
        }
        return 0;
    }


    public interface MovieVideosOnClickHandler {
        void onVideoClick(Video video);
    }

    public class MovieVideosAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Video mVideo;

        public MovieVideosAdapterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            mMovieVideosOnClickHandler.onVideoClick(mVideo);
        }

        private  void bindTo(Video video) {


        }

    }


}
