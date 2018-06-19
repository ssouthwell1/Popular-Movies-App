package com.udacity.pilotsham.popular_movies_app.presenter;

public interface MovieDetailPresenter {

    void getMovieVideosById(int movieId);

    void getReviewsById(int movieId);

    void markAsFavorite();

    void removeFromFavorites();

    void onDestroy();

    void onResume();
}
