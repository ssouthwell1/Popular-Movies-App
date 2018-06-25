package com.udacity.pilotsham.popular_movies_app.presenter;

public interface MovieDetailPresenter extends BasePresenter {

    void getMovieVideosById(int movieId);

    void getReviewsById(int movieId);
    
    void onDestroy();

    void onResume();
}
