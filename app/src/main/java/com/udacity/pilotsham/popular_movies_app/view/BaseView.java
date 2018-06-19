package com.udacity.pilotsham.popular_movies_app.view;

import com.udacity.pilotsham.popular_movies_app.model.Movie;

public interface BaseView {

    void addToFavorites(Movie movie);

    void removeFromFavorites(Movie movie);


}
