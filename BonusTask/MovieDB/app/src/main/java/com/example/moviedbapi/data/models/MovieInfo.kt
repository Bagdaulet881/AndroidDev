package com.example.moviedbapi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "movie_table")
data class MovieData(
//    @PrimaryKey(autoGenerate = true)

    @SerializedName("id") val id: Int? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genres") val genres: List<Genre>? = null
)

data class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class Story(
    val storyId: Int? = null,
    val title: String? = null,
    val storyImgPath: Int? = null
)