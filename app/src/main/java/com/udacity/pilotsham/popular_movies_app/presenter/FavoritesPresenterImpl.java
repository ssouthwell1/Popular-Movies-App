package com.udacity.pilotsham.popular_movies_app.presenter;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.udacity.pilotsham.popular_movies_app.model.MovieContract;

public class FavoritesPresenterImpl implements FavoritesPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ID_FAVORITE_LOADER = 44;

    @Override
    public boolean isFavorite() {
        return false;
    }

    @Override
    public void addToFavorites() {

    }

    @Override
    public void removeFromFavorites() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch (id) {
            case ID_FAVORITE_LOADER:
            Uri movieLoader = MovieContract.MovieEntry.CONTENT_URI;

        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
