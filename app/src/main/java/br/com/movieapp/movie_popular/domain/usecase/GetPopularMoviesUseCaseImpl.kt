package br.com.movieapp.movie_popular.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_popular.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviePopularRepository
) : GetPopularMoviesUseCase {
    override fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies(
            PagingConfig(
            // quantos itens vao ser retornado por pagina
            pageSize = 20,
            // primeiro carregamento quantos itens devem ser trazidos
            initialLoadSize = 20
        )
        )
    }

}