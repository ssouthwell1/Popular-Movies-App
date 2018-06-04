package com.udacity.pilotsham.popular_movies_app.api;

import android.support.annotation.NonNull;

import com.udacity.pilotsham.popular_movies_app.model.MovieResponse;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.Observable;

public class MovieAPIClient {

    private MovieAPIService movieAPIService;
    private static MovieAPIClient movieAPIClientInstance;

    private MovieAPIClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(StringUtils.BASE_URL).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();

        movieAPIService = retrofit.create(MovieAPIService.class);


    }

    public static synchronized MovieAPIClient getInstance() {
        if (movieAPIClientInstance == null) {
            movieAPIClientInstance = new MovieAPIClient();

        }
        return movieAPIClientInstance;
    }

    public Observable<MovieResponse> getPopularMovies(@NonNull String apiKey) {

        return movieAPIService.getPopularMovies(apiKey);

    }

    public Observable<MovieResponse> getTopRatedMovies(@NonNull String apiKey) {
        return movieAPIService.getTopRatedMovies(apiKey);
    }


}
