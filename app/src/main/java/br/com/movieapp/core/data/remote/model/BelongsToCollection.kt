package br.com.movieapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("backdropPath")
    val backdrop_path: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("posterPath")
    val poster_path: String
)