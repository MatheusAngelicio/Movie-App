package br.com.movieapp.movie_detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_detail.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail.presentation.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)

    }

    private fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        movieId = event.movieId
                    ).collect { resultData ->
                        when (resultData) {
                            is ResultData.Success -> {
                                //faço uma copia do objeto, pra conseguir atualizar so o que eu quero
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = resultData.data.second,
                                    results = resultData.data.first
                                )
                            }

                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = resultData.e.message.toString()
                                )
                                UtilFunctions.logError(
                                    "DETAIL-ERROR",
                                    resultData.e.message.toString()
                                )
                            }

                            is ResultData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true
                                )
                            }
                        }

                    }
                }
            }
        }

    }
}