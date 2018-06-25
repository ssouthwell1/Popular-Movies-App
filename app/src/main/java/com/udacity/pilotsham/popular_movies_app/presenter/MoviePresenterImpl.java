package com.udacity.pilotsham.popular_movies_app.presenter;

import android.util.Log;

import com.udacity.pilotsham.popular_movies_app.BuildConfig;
import com.udacity.pilotsham.popular_movies_app.api.MovieAPIClient;
import com.udacity.pilotsham.popular_movies_app.model.MovieResponse;
import com.udacity.pilotsham.popular_movies_app.view.MovieView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MoviePresenterImpl implements MoviePresenter {
    private MovieView movieView;
    private String TAG = "MoviePresenterImpl";

    public MoviePresenterImpl(MovieView movieView) {
        this.movieView = movieView;
    }


    @Override
    public void getPopularMovies() {
        MovieAPIClient.getInstance()
                .getPopularMovies(BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MovieResponse>() {

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        movieView.displayMovies(movieResponse);
                        Log.d(TAG, "Movie responses received: " + movieResponse.getResults().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieView.displayError("Error fetching movie Data");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        movieView.hideProgress();
                        Log.d(TAG, "Progress bar hidden");
                    }
                });

    }

    @Override
    public void getTopRatedMovies() {
        MovieAPIClient.getInstance()
                .getTopRatedMovies(BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MovieResponse>() {


                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        movieView.displayMovies(movieResponse);
                        Log.d(TAG, "Movie responses received: " + movieResponse.getResults().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieView.displayError("Error fetching movie Data");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        movieView.hideProgress();
                        Log.d(TAG, "Progress bar hidden");
                    }
                });
    }
    
    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }
}

