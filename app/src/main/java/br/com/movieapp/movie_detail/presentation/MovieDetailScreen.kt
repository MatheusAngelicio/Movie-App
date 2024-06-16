package br.com.movieapp.movie_detail.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.movie_detail.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail.presentation.state.MovieDetailState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    id: Int?,
    uiState: MovieDetailState,
    getMovieDetail: (MovieDetailEvent.GetMovieDetail) -> Unit
) {
    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    //ao utilizar o LaunchedEffect estamos criando um fluxo assincrono
    // separado pra pegar os detalhes do filme
    // passando o key1 = true
    LaunchedEffect (key1 = true){
        id?.let {
            // aqui eu chamo a api, e no content eu observo a resposta assincrona
            getMovieDetail(MovieDetailEvent.GetMovieDetail(it))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = stringResource(id = R.string.detail_movie), color = white)
                },
                backgroundColor = black
            )
        },
        content = {
            MovieDetailContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMoviesSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                onAddFavorite = {}
            )

        }
    )

}