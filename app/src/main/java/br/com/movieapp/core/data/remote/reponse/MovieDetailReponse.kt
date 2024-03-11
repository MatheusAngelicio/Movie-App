package br.com.movieapp.core.data.remote.reponse

import br.com.movieapp.core.data.remote.model.BelongsToCollection
import br.com.movieapp.core.data.remote.model.Genre
import br.com.movieapp.core.data.remote.model.ProductionCompany
import br.com.movieapp.core.data.remote.model.ProductionCountry
import br.com.movieapp.core.data.remote.model.SpokenLanguage
import com.google.gson.annotations.SerializedName

data class MovieDetailReponse(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdropPath")
    val backdrop_path: String,

    @SerializedName("belongsToCollection")
    val belongs_to_collection: BelongsToCollection,

    @SerializedName("budget")
    val budget: Int,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("homepage")
    val homepage: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("imdbId")
    val imdb_id: String,

    @SerializedName("originallanguage")
    val original_language: String,

    @SerializedName("originalTitle")
    val original_title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("posterPath")
    val poster_path: String,

    @SerializedName("productionCompanies")
    val production_companies: List<ProductionCompany>,

    @SerializedName("productionCountries")
    val production_countries: List<ProductionCountry>,

    @SerializedName("releaseDate")
    val release_date: String,

    @SerializedName("revenue")
    val revenue: Long,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("spokenLanguages")
    val spoken_languages: List<SpokenLanguage>,

    @SerializedName("status")
    val status: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("voteAverage")
    val vote_average: Double,

    @SerializedName("voteCount")
    val vote_count: Int
)