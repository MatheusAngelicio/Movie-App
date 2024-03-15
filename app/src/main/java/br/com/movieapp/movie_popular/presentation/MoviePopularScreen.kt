package br.com.movieapp.movie_popular.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_popular.presentation.components.MovieContent
import br.com.movieapp.movie_popular.presentation.state.MoviePopularState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    onClick: (id: Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.popular_movies),
                        color = white
                    )
                },
                backgroundColor = black
            )
        },
        content = { paddingValues ->
            MovieContent(
                pagingMovies = movies,
                paddingValues = paddingValues,
            ) { movieId ->
                UtilFunctions.logInfo("MOVIE_ID", movieId.toString())
                onClick(movieId)
            }
        }
    )

}

@Preview
@Composable
private fun MoviePopularScreenPreview() {
    //MoviePopularScreen()
}