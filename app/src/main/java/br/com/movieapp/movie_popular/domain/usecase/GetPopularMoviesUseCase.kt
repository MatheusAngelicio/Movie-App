package br.com.movieapp.movie_popular.domain.usecase

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

