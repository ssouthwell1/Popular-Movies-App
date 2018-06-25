package com.udacity.pilotsham.popular_movies_app.view;

import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.model.ReviewResponse;
import com.udacity.pilotsham.popular_movies_app.model.VideoResponse;

public interface MovieDetailView {

    void displayVideos(VideoResponse videoResponse);

    void displayReviews(ReviewResponse reviewResponse);

    void displayError(String error);

    void setMovieData();


}
