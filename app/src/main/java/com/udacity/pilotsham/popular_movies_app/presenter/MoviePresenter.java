package com.udacity.pilotsham.popular_movies_app.presenter;


public interface MoviePresenter {

    void getPopularMovies();

    void getTopRatedMovies();

    void onDestroy();

    void onResume();


}
