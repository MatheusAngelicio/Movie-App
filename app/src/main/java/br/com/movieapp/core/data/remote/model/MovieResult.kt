package br.com.movieapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdropPath")
    val backdrop_path: String,

    @SerializedName("genreIds")
    val genre_ids: List<Int>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("originalLanguage")
    val original_language: String,

    @SerializedName("originalTitle")
    val original_title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("posterPath")
    val poster_path: String,

    @SerializedName("releaseDate")
    val release_date: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("voteAverage")
    val vote_average: Double,

    @SerializedName("voteCount")
    val vote_count: Int
)