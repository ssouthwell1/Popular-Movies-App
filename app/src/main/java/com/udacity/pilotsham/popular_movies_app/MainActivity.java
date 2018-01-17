package com.udacity.pilotsham.popular_movies_app;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    ProgressBar mLoadingBar;

    RecyclerView mRecyclerView;


    ArrayList<Movie> mMovies = new ArrayList<>();

    MovieAdapter mMovieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingBar = ((ProgressBar) findViewById(R.id.pb_movie_loading));
        mRecyclerView = ((RecyclerView) findViewById(R.id.rv_movie_grid));
        mDrawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));
        // mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mMovieAdapter = new MovieAdapter(MainActivity.this, mMovies, this);
        mRecyclerView.setAdapter(mMovieAdapter);
        mRecyclerView.setHasFixedSize(true);

        loadMovieData(StringUtils.POPULAR_MOVIE_PARAM);

    }

    private void loadMovieData(String sortByParam) {
        new MovieQueryTask().execute(sortByParam);
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(StringUtils.MOVIE_EXTRA, movie);
        startActivity(intent);
    }


    public class MovieQueryTask extends AsyncTask<String, Void, ArrayList<Movie>> {
        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {

            Type listType = new TypeToken<ArrayList<Movie>>() {
            }.getType();

            Gson gson =
                    new GsonBuilder()
                            .registerTypeAdapter(listType, new MovieDeserializer())
                            .create();


            String sortByParam = strings[0];
            URL url = NetworkUtils.buildSortByUrl(sortByParam);

            try {

                JsonElement response = NetworkUtils.getResponseFromHttpUrl(url);
                mMovies = gson.fromJson(response, listType);

            } catch (IOException e) {
                e.printStackTrace();

            }

            return mMovies;
        }


        @Override
        protected void onPreExecute() {
            mLoadingBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            mLoadingBar.setVisibility(View.INVISIBLE);
            super.onPostExecute(movies);

            if (movies.size() != 0) {
                mMovieAdapter.setMovieData(movies);
                mRecyclerView.setVisibility(View.VISIBLE);

            }
        }
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

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.action_sort_by_popular) {
            loadMovieData(StringUtils.POPULAR_MOVIE_PARAM);
        } else if (id == R.id.action_sort_by_rating) {
            loadMovieData(StringUtils.TOP_RATED_MOVIE_PARAM);
        }

        return super.onOptionsItemSelected(item);

    }
}
