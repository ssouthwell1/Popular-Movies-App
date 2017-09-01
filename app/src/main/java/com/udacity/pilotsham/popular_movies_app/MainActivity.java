package com.udacity.pilotsham.popular_movies_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar mLoadingBar;

    RecyclerView mRecyclerView;
    //MovieAdapter mMovieAdapter;

    TextView textView;

    ArrayList<Movie> mMovies = new ArrayList<>();

    MovieAdapter mMovieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingBar = ((ProgressBar) findViewById(R.id.pb_movie_loading));
        mRecyclerView = ((RecyclerView) findViewById(R.id.rv_movie_grid));



        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
//        mMovieAdapter = new MovieAdapter(MainActivity.this, mMovies);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        loadMovieData();

    }

    private void loadMovieData() {
        new MovieQueryTask().execute(StringUtils.POPULAR_MOVIE_PARAM);
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
                mMovieAdapter = new MovieAdapter(MainActivity.this, mMovies);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        }
    }


}
