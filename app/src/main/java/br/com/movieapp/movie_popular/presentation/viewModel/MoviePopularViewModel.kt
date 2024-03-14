package br.com.movieapp.movie_popular.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.movieapp.movie_popular.domain.usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popular.presentation.state.MoviePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MoviePopularState())
        private set
    //  private set para ninguem conseguir setar o valor fora do viewModel

    init {
        val movies = getPopularMoviesUseCase.invoke()
            .cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)
    }

}