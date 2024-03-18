package br.com.movieapp.search_movie.domain.source

import br.com.movieapp.core.data.remote.reponse.MovieResponse
import br.com.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource

    suspend fun getSearchMovies(page: Int, query: String): MovieResponse
}