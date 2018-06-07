package com.udacity.pilotsham.popular_movies_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.pilotsham.popular_movies_app.model.Movie;
import com.udacity.pilotsham.popular_movies_app.utilities.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.movie_user_rating)
    TextView mUserRating;

    @BindView(R.id.movie_overview)
    TextView mOverview;

    @BindView(R.id.movie_title)
    TextView mTitle;

    @BindView(R.id.movie_release_date)
    TextView mReleaseDate;

    @BindView(R.id.movie_poster)
    ImageView mPosterImage;

    @BindView(R.id.movie_backdrop)
    ImageView mThumbnailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        Intent parentActivity = getIntent();


        if (parentActivity != null) {

            if (parentActivity.hasExtra(StringUtils.MOVIE_EXTRA)) {
                Movie movieInstance = parentActivity.getParcelableExtra(StringUtils.MOVIE_EXTRA);
                String userRatingString = Double.toString(movieInstance.getVoteAverage());
                mUserRating.setText(userRatingString);
                mOverview.setText(movieInstance.getOverview());
                mReleaseDate.setText(movieInstance.getReleaseDate());
                mTitle.setText(movieInstance.getTitle());


                /* Refactor this later */

                Uri uri = Uri.parse(StringUtils.BASE_POSTER_IMAGE_URL).buildUpon().appendEncodedPath(StringUtils.DEFAULT_MOVIE_POSTER_SIZE)
                        .appendEncodedPath(movieInstance.getPosterPath())
                        .build();
                Uri uri2 = Uri.parse(StringUtils.BASE_POSTER_IMAGE_URL).buildUpon().appendEncodedPath(StringUtils.DEFAULT_MOVIE_POSTER_SIZE)
                        .appendEncodedPath(movieInstance.getBackdropPath())
                        .build();

                /* Repeated code here needs to be changed, just checking for Testing purposes */

                Picasso.with(this).load(uri).into(mPosterImage);
                Picasso.with(this).load(uri2).into(mThumbnailImage);


            }

        }


    }


}