package br.com.movieapp.movie_popular.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.reponse.MovieResponse
import br.com.movieapp.core.paging.MoviePagingSource
import br.com.movieapp.movie_popular.domain.source.MoviePopularRemoteDataSource

class MoviePopularRemoteDataSourceImpl (
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page)
    }
}