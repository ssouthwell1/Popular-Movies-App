package com.udacity.pilotsham.popular_movies_app;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by shamarisouthwell on 8/30/17.
 */

public class MovieDeserializer implements JsonDeserializer<ArrayList<Movie>> {


    @Override
    public ArrayList<Movie> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final String OWM_RESULTS = "results";

        JsonElement results = json.getAsJsonObject().get(OWM_RESULTS).getAsJsonArray();


        return new Gson().fromJson(results, typeOfT);
    }
}
