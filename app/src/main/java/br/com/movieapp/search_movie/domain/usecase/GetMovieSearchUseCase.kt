package br.com.movieapp.search_movie.domain.usecase

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMovieSearchUseCase {
    operator fun invoke(query: String): Flow<PagingData<Movie>>
}

