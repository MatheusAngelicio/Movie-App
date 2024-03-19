package br.com.movieapp.search_movie.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.search_movie.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieSearchUseCaseImpl @Inject constructor(
    private val repository: MovieSearchRepository
) : GetMovieSearchUseCase {

    override fun invoke(query: String): Flow<PagingData<Movie>> {
        return repository.getSearchMovies(
            query, PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                )
        )
    }

}