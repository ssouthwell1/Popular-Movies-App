package com.udacity.pilotsham.popular_movies_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.udacity.pilotsham.popular_movies_app.model.Movie;

import java.util.List;

public class MovieResponse {

    @SerializedName("page")
    @Expose
    Integer page;

    @SerializedName("total_results")
    @Expose
    Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    Integer totalPages;

    @SerializedName("results")
    @Expose
    List<Movie> results;

    public MovieResponse() {
        /*
        Empty constructor for use in JSON serialization
         */

    }

    public MovieResponse(Integer page, Integer totalResults, Integer totalPages, List<Movie> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
