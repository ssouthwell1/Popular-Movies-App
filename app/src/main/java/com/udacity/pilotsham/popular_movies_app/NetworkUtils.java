package com.udacity.pilotsham.popular_movies_app;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by shamarisouthwell on 8/30/17.
 */

public class NetworkUtils extends AppCompatActivity {


    private static final String TAG = NetworkUtils.class.getSimpleName();




    public static URL buildSortByUrl(String sortByParam) {

        Uri builtUri = Uri.parse(StringUtils.MOVIE_URL).buildUpon()
                .appendEncodedPath(sortByParam).appendQueryParameter(StringUtils.API_KEY_PARAM, StringUtils.API_KEY).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built Url: " + url);
        return url;

    }


    public static JsonElement getResponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


        try {
            InputStream in = urlConnection.getInputStream();
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(new InputStreamReader(in));


            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return element;
            } else {

                System.out.print(urlConnection.getResponseCode());
                return null;


            }
        } finally {
            urlConnection.disconnect();
        }
    }




}






