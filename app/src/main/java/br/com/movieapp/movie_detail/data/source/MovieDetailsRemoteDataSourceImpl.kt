package br.com.movieapp.movie_detail.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.reponse.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.paging.MovieSimilarPagingSource
import br.com.movieapp.core.util.toImageUrl
import br.com.movieapp.movie_detail.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailsRemoteDataSource {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        val genres = response.genres.map { it.name }

        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toImageUrl(),
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse {
        return service.getMoviesSimilar(page, movieId)
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(this, movieId)
    }
}