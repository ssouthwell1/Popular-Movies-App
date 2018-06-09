package com.udacity.pilotsham.popular_movies_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.pilotsham.popular_movies_app.R;
import com.udacity.pilotsham.popular_movies_app.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewsAdapterViewHolder> {

    private static final String TAG = MovieReviewsAdapter.class.getSimpleName();
    List<Review> mReviews;
    private Context mContext;
    private MovieReviewsAdapter.MovieReviewsAdapterOnClickHandler mMovieAdapterOnClickHandler;


    public interface MovieReviewsAdapterOnClickHandler {
        void onReviewClick(Review review);
    }

    public MovieReviewsAdapter(Context context, List<Review> reviews, MovieReviewsAdapterOnClickHandler movieReviewsAdapterOnClickHandler) {
        this.mContext = context;
        this.mReviews = reviews;
        this.mMovieAdapterOnClickHandler = movieReviewsAdapterOnClickHandler;
    }

    @NonNull
    @Override
    public MovieReviewsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * Inflate the list item view on its creation and setup the adapter viewholder with the specified views
         */

        this.mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.review_list_item, parent, false);
        return new MovieReviewsAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewsAdapterViewHolder holder, int position) {
        Review review = mReviews.get(position);
        holder.bindTo(review);
    }


    @Override
    public int getItemCount() {
        if (mReviews == null) {
            return 0;
        }
        return mReviews.size();
    }


    public class MovieReviewsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Review mReview;
        @BindView(R.id.tv_review_author)
        TextView mReviewAuthor;
        @BindView(R.id.tv_review_content)
        TextView mReviewContent;

        private MovieReviewsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onClick(View v) {
            mMovieAdapterOnClickHandler.onReviewClick(mReview);
        }


        private void bindTo(Review review) {
            mReview = review;
            mReviewAuthor.setText(review.getAuthor());
            mReviewContent.setText(review.getContent());


        }


    }


}
