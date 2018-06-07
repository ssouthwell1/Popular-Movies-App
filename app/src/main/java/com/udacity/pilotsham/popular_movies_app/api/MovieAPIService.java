package com.udacity.pilotsham.popular_movies_app.api;

import com.udacity.pilotsham.popular_movies_app.model.MovieResponse;
import com.udacity.pilotsham.popular_movies_app.model.ReviewResponse;
import com.udacity.pilotsham.popular_movies_app.model.VideoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieAPIService {

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Observable<VideoResponse> getMovieVideosById(@Query("api_key") String apiKey, @Path("movie_id") int id);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewResponse> getMovieReviewsById(@Query("api_key") String apiKey, @Path("movie_id") int id);


}
