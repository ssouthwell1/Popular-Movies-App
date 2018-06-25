package com.udacity.pilotsham.popular_movies_app.view;

import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.model.MovieResponse;

public interface MovieView {


    void displayMovies(MovieResponse movieResponse);

    void displayError(String error);

    void showProgress();

    void hideProgress();


}
