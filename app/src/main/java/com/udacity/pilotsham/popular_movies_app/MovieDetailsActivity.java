package com.udacity.pilotsham.popular_movies_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;


public class MovieDetailsActivity extends AppCompatActivity implements MovieReviewsAdapter.MovieReviewsAdapterOnClickHandler, MovieVideosAdapter.MovieVideosAdapterOnClickHandler, MovieDetailView {

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

    @BindViews({R.id.rating_star_1, R.id.rating_star_2, R.id.rating_star_3, R.id.rating_star_4, R.id.rating_star_5})
    List<ImageView> ratingStars;

    @BindView(R.id.btn_favorite)
    Button mFavoriteButton;

    @BindView(R.id.tv_reviews_title)
    TextView mReviewTitleTextView;

    @BindView(R.id.tv_trailers_title)
    TextView mVideoTitleTextView;

    @BindView(R.id.movie_detail_toolbar)
    Toolbar mToolbar;

    MovieDetailPresenter movieDetailPresenter;

    ArrayList<Review> mReviews;

    ArrayList<Video> mVideos;

    Movie movieInstance;

    MovieReviewsAdapter mMovieReviewsAdapter;

    MovieVideosAdapter mMovieVideosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        init();
        setMovieData();
        loadReviews();
        loadVideos();

        if (savedInstanceState != null && savedInstanceState.containsKey(StringUtils.VIDEOS_EXTRA)) {
            mVideos = savedInstanceState.getParcelableArrayList(StringUtils.VIDEOS_EXTRA);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(StringUtils.REVIEWS_EXTRA)) {
            mReviews = savedInstanceState.getParcelableArrayList(StringUtils.REVIEWS_EXTRA);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mVideos != null && !mVideos.isEmpty()) {
            /**
             See what happens onSaveInstanceState here
             */
            outState.putParcelableArrayList(StringUtils.VIDEOS_EXTRA, mVideos);

        }
        if (mReviews != null && !mReviews.isEmpty()) {
            outState.putParcelableArrayList(StringUtils.REVIEWS_EXTRA, mReviews);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movie_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void loadReviews() {
        movieDetailPresenter.getReviewsById(movieInstance.getId());
    }

    private void loadVideos() {
        movieDetailPresenter.getMovieVideosById(movieInstance.getId());
    }

    @Override
    public void onReviewClick(Review review) {
        if (review != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(review.getUrl()));
            startActivity(intent);
        }
    }

    @Override
    public void onVideoClick(Video video) {
        if (video != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getVideoUrl() + video.getKey()));
            startActivity(intent);
        }
    }

    @Override
    public void displayVideos(VideoResponse videoResponse) {
        if (videoResponse != null && !videoResponse.getResults().isEmpty()) {
            mVideos = videoResponse.getResults();
            mMovieVideosAdapter = new MovieVideosAdapter(this, mVideos, this::onVideoClick);
            mVideosRecyclerView.setAdapter(mMovieVideosAdapter);
        } else {
            mVideoTitleTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void displayReviews(ReviewResponse reviewResponse) {
        if (reviewResponse != null && !reviewResponse.getResults().isEmpty()) {
            mReviews = reviewResponse.getResults();
            mMovieReviewsAdapter = new MovieReviewsAdapter(this, reviewResponse.getResults(), this);
            mReviewsRecyclerView.setAdapter(mMovieReviewsAdapter);


        } else mReviewTitleTextView.setVisibility(View.INVISIBLE);

    }


    @Override
    public void displayError(String error) {

    }

    private void setRatings() {
        Double userRating = movieInstance.getVoteAverage() / 2;

        for (int i = 0; i < userRating.intValue(); i++) {
            ratingStars.get(i).setImageResource(R.drawable.ic_star);
        }

        if (Math.round(userRating) > userRating.intValue()) {
            ratingStars.get(userRating.intValue()).setImageResource(R.drawable.ic_star_half);
        }


    }

    @Override
    public void setMovieData() {
        String userRatingString = Double.toString(movieInstance.getVoteAverage());
        mUserRating.setText(userRatingString);
        mOverview.setText(movieInstance.getOverview());
        mReleaseDate.setText(movieInstance.getReleaseDate());
        mTitle.setText(movieInstance.getTitle());
        getSupportActionBar().setTitle(movieInstance.getTitle());
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
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        movieDetailPresenter = new MovieDetailPresenterImpl(this);
        mReviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mVideosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Intent parentActivity = getIntent();

        if (parentActivity != null) {

            if (parentActivity.hasExtra(StringUtils.MOVIE_EXTRA)) {
                movieInstance = parentActivity.getParcelableExtra(StringUtils.MOVIE_EXTRA);


            }

        }

    }


    @Override
    public void addToFavorites(Movie movie) {

    }

    @Override
    public void removeFromFavorites(Movie movie) {

    }
}


