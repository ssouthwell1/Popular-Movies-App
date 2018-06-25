package com.udacity.pilotsham.popular_movies_app.presenter;

import com.udacity.pilotsham.popular_movies_app.model.Movie;

public interface BasePresenter {

    boolean isFavorite();

    void addToFavorites();

    void removeFromFavorites();

}
