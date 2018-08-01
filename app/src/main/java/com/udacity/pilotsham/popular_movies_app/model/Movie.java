package com.udacity.pilotsham.popular_movies_app.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shamarisouthwell on 8/30/17.
 */

@Entity(tableName = "favorites")
public class Movie implements Parcelable {


    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = "userRating")
    private Double userRating;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = "overview")
    private String overview;

    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "release_date")
    private String releaseDate;


//    public Movie(int id, String title, String poster, String overview, String userRating,
//                 String releaseDate, String backdrop) {
//        this.id = id;
//        this.title = title;
//        this.posterPath = poster;
//        this.overview = overview;
//        this.userRating = userRating;
//        this.releaseDate = releaseDate;
//        this.backdropPath = backdrop;
//    }


    public final static Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel parcel) {
            Movie movie = new Movie();
            movie.backdropPath = parcel.readString();
            movie.id = parcel.readInt();
            movie.title = parcel.readString();
            movie.releaseDate = parcel.readString();
            movie.posterPath = parcel.readString();
            movie.overview = parcel.readString();
            movie.userRating = parcel.readDouble();


            return movie;
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[0];
        }

    };

//    @Nullable
//    public String getPosterUrl(Context context) {
//        if (posterPath != null && !posterPath.isEmpty()) {
//            return context.getResources().getString()
//        }
//
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Double getVoteAverage() {
        return userRating;
    }

    public void setVoteAverage(Double userRating) {
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(backdropPath);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeDouble(userRating);

    }
}
