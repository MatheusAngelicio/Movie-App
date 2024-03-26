package br.com.movieapp.movie_detail.domain.usecase

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {
    operator fun invoke(movieId: Int): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>>
}