package br.com.movieapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    val id: Int,

    @SerializedName("logoPath")
    val logo_path: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("originCountry")
    val origin_country: String
)