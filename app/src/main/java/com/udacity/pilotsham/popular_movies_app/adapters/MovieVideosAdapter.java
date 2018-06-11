package com.udacity.pilotsham.popular_movies_app.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.pilotsham.popular_movies_app.R;
import com.udacity.pilotsham.popular_movies_app.model.Review;
import com.udacity.pilotsham.popular_movies_app.model.Video;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieVideosAdapter extends RecyclerView.Adapter<MovieVideosAdapter.MovieVideosAdapterViewHolder> {
    private List<Video> mVideos;
    private Context mContext;
    private MovieVideosAdapterOnClickHandler mMovieVideosAdapterOnClickHandler;
    public final String TAG = MovieVideosAdapter.class.getSimpleName();

    public MovieVideosAdapter(Context context, List<Video> videos, MovieVideosAdapterOnClickHandler movieVideosAdapterOnClickHandler) {
        this.mContext = context;
        this.mVideos = videos;
        this.mMovieVideosAdapterOnClickHandler = movieVideosAdapterOnClickHandler;
    }

    @NonNull
    @Override
    public MovieVideosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.video_list_item, parent, false);
        return new MovieVideosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVideosAdapterViewHolder holder, int position) {
        Video video = mVideos.get(position);
        holder.bindTo(video);
    }

    @Override
    public int getItemCount() {
        if (mVideos != null) {
            return mVideos.size();
        }
        return 0;
    }


    public interface MovieVideosAdapterOnClickHandler {
        void onVideoClick(Video video);
    }

    public class MovieVideosAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Video mVideo;
        @BindView(R.id.movie_video_thumbnail)
        public ImageView mVideoThumbnailView;
        @BindView(R.id.image_btn_play_video)
        ImageButton mVideoPlayButton;


        private MovieVideosAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mVideoPlayButton.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            mMovieVideosAdapterOnClickHandler.onVideoClick(mVideo);
        }

        private void bindTo(Video video) {
            mVideo = video;
            String thumbnailUrl = StringUtils.YOUTUBE_VIDEO_URL + video.getKey() + StringUtils.YOUTUBE_VIDEO_THUMBNAIL_PATH;
            Picasso.with(mContext).load(thumbnailUrl).into(mVideoThumbnailView);
            Picasso.with(mContext).setLoggingEnabled(true);

        }

    }


}
