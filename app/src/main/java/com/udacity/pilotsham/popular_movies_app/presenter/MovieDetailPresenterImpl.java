package com.udacity.pilotsham.popular_movies_app.presenter;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.udacity.pilotsham.popular_movies_app.BuildConfig;
import com.udacity.pilotsham.popular_movies_app.api.MovieAPIClient;
import com.udacity.pilotsham.popular_movies_app.model.ReviewResponse;
import com.udacity.pilotsham.popular_movies_app.model.VideoResponse;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;
import com.udacity.pilotsham.popular_movies_app.view.MovieDetailView;
import com.udacity.pilotsham.popular_movies_app.view.MovieView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private MovieDetailView movieDetailView;
    private String TAG = "MovieDetailPresenterImpl";
    private SQLiteDatabase sqLiteDatabase;

    public MovieDetailPresenterImpl(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
    }

    @Override
    public void getMovieVideosById(int movieId) {
        MovieAPIClient.getInstance().getMovieVideosById(movieId, BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<VideoResponse>() {
                    @Override
                    public void onNext(VideoResponse videoResponse) {
                        movieDetailView.displayVideos(videoResponse);
                        Log.d(TAG, "Movie videos: " + videoResponse.getResults().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieDetailView.displayError("Error fetching video data");
                    }

                    @Override
                    public void onComplete() {
                        // Do nothing
                        // Add progress bar to the entire screen
                    }
                });
    }

    @Override
    public void getReviewsById(int movieId) {
        MovieAPIClient.getInstance().getMovieReviewsById(movieId, BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ReviewResponse>() {
                    @Override
                    public void onNext(ReviewResponse reviewResponse) {
                        movieDetailView.displayReviews(reviewResponse);
                        Log.d(TAG, "Movie responses: " + reviewResponse.getResults().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieDetailView.displayError("Error fetching review data");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        // Do nothing
                        // Add progress bar to the entire screen
                    }
                });
    }

    @Override
    public void markAsFavorite() {

    }

    @Override
    public void removeFromFavorites() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }
}
