package br.com.movieapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("englishName")
    val english_name: String,

    @SerializedName("iso6391")
    val iso_639_1: String,

    @SerializedName("name")
    val name: String
)