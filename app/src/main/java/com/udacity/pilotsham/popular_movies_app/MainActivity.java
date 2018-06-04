package com.udacity.pilotsham.popular_movies_app;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.udacity.pilotsham.popular_movies_app.adapters.MovieAdapter;
import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.model.MovieResponse;
import com.udacity.pilotsham.popular_movies_app.presenter.MoviePresenterImpl;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;
import com.udacity.pilotsham.popular_movies_app.view.MovieDetailsActivity;
import com.udacity.pilotsham.popular_movies_app.view.MovieView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler, MovieView {

    private MoviePresenterImpl moviePresenter;

    List<Movie> mMovies = new ArrayList<>();

    MovieAdapter mMovieAdapter;

    @BindView(R.id.pb_movie_loading)
    ProgressBar mLoadingBar;

    @BindView(R.id.rv_movie_grid)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        init();
        loadMovieData(StringUtils.POPULAR_MOVIE_PARAM);


    }

    public void init() {
        /**
         * Create Grid layout for recycler view to span 2 vertical columns and setup Movie Presenter
         */
        moviePresenter = new MoviePresenterImpl(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

    }

    private void loadMovieData(String sortByParam) {
        if (sortByParam.equalsIgnoreCase(StringUtils.POPULAR_MOVIE_PARAM)) {
            moviePresenter.getPopularMovies();
        } else if (sortByParam.equalsIgnoreCase(StringUtils.TOP_RATED_MOVIE_PARAM))
            moviePresenter.getTopRatedMovies();

    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(StringUtils.MOVIE_EXTRA, movie);
        startActivity(intent);
    }


    @Override
    public void displayMovies(MovieResponse movieResponse) {
        /**
         * Method to add movie results from API response to Activity and set adapter in order to display the results
         */

        if (movieResponse != null) {
            mMovies = movieResponse.getResults();
            mMovieAdapter = new MovieAdapter(this, mMovies, this);
            mRecyclerView.setAdapter(mMovieAdapter);
            mRecyclerView.setHasFixedSize(true);

        }

    }



    @Override
    public void showProgress() {
        mLoadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoadingBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayError(String error) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sort, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort_by_popular) {
            loadMovieData(StringUtils.POPULAR_MOVIE_PARAM);
        } else if (id == R.id.action_sort_by_rating) {
            loadMovieData(StringUtils.TOP_RATED_MOVIE_PARAM);
        }

        return super.onOptionsItemSelected(item);

    }
}
