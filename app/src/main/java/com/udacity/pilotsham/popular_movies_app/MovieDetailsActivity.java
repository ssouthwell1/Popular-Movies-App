package com.udacity.pilotsham.popular_movies_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.pilotsham.popular_movies_app.adapters.MovieReviewsAdapter;
import com.udacity.pilotsham.popular_movies_app.adapters.MovieVideosAdapter;
import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.model.Review;
import com.udacity.pilotsham.popular_movies_app.model.ReviewResponse;
import com.udacity.pilotsham.popular_movies_app.model.Video;
import com.udacity.pilotsham.popular_movies_app.model.VideoResponse;
import com.udacity.pilotsham.popular_movies_app.presenter.MovieDetailPresenter;
import com.udacity.pilotsham.popular_movies_app.presenter.MovieDetailPresenterImpl;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;
import com.udacity.pilotsham.popular_movies_app.view.MovieDetailView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailsActivity extends AppCompatActivity implements MovieReviewsAdapter.MovieReviewsAdapterOnClickHandler, MovieVideosAdapter.MovieVideosOnClickHandler, MovieDetailView {

    @BindView(R.id.movie_user_rating)
    TextView mUserRating;

    @BindView(R.id.movie_overview)
    TextView mOverview;

    @BindView(R.id.movie_title)
    TextView mTitle;

    @BindView(R.id.movie_release_date)
    TextView mReleaseDate;

    @BindView(R.id.movie_poster)
    ImageView mPosterImage;

    @BindView(R.id.movie_backdrop)
    ImageView mThumbnailImage;

    @BindView(R.id.rv_reviews)
    RecyclerView mReviewsRecyclerView;

    @BindView(R.id.rv_videos)
    RecyclerView mVideosRecyclerView;

    MovieDetailPresenter movieDetailPresenter;

    List<Review> mReviews;

    List<Video> mVideos;

    Movie movieInstance;

    List<ImageView> ratingStars;

    MovieReviewsAdapter mMovieReviewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        init();
        setMovieData();
        loadReviews();


    }


    public void loadReviews() {
        movieDetailPresenter.getReviewsById(movieInstance.getId());
    }

    public void loadVideos() {
        movieDetailPresenter.getMovieVideosById(movieInstance.getId());
    }

    @Override
    public void onReviewClick(Review review) {

    }

    @Override
    public void onVideoClick(Video video) {

    }

    @Override
    public void displayVideos(VideoResponse videoResponse) {
        if (videoResponse != null) {

        }
        //movieDetailPresenter.getMovieVideosById(videoResponse.getId());
    }

    @Override
    public void displayReviews(ReviewResponse reviewResponse) {
        if (reviewResponse != null) {
            mReviews = reviewResponse.getResults();
            mMovieReviewsAdapter = new MovieReviewsAdapter(this, reviewResponse.getResults(), this);
            mReviewsRecyclerView.setAdapter(mMovieReviewsAdapter);


        }

    }

    @Override
    public void displayError(String error) {

    }

    private void setRatings() {
        Double userRating = movieInstance.getVoteAverage() / 2;
        
        movieInstance.getVoteAverage();
    }

    @Override
    public void setMovieData() {
        String userRatingString = Double.toString(movieInstance.getVoteAverage());
        mUserRating.setText(userRatingString);
        mOverview.setText(movieInstance.getOverview());
        mReleaseDate.setText(movieInstance.getReleaseDate());
        mTitle.setText(movieInstance.getTitle());
        /* Refactor this later */

        Uri uri = Uri.parse(StringUtils.BASE_POSTER_IMAGE_URL).buildUpon().appendEncodedPath(StringUtils.DEFAULT_MOVIE_POSTER_SIZE)
                .appendEncodedPath(movieInstance.getPosterPath())
                .build();
        Uri uri2 = Uri.parse(StringUtils.BASE_POSTER_IMAGE_URL).buildUpon().appendEncodedPath(StringUtils.DEFAULT_MOVIE_POSTER_SIZE)
                .appendEncodedPath(movieInstance.getBackdropPath())
                .build();

        /* Repeated code here needs to be changed, just checking for Testing purposes */

        Picasso.with(this).load(uri).into(mPosterImage);
        Picasso.with(this).load(uri2).into(mThumbnailImage);

        setRatings();
    }

    private void init() {
        movieDetailPresenter = new MovieDetailPresenterImpl(this);
        mReviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Intent parentActivity = getIntent();

        if (parentActivity != null) {

            if (parentActivity.hasExtra(StringUtils.MOVIE_EXTRA)) {
                movieInstance = parentActivity.getParcelableExtra(StringUtils.MOVIE_EXTRA);


            }

        }

    }


}


