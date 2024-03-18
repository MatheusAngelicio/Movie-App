package br.com.movieapp.search_movie.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.reponse.MovieResponse
import br.com.movieapp.core.paging.MovieSearchPagingSource
import br.com.movieapp.search_movie.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {

    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query, this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): MovieResponse {
        return service.searchMovie(page, query)
    }
}