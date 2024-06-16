package br.com.movieapp.movie_detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.movie_popular.presentation.components.MovieItem

@Composable
fun MovieDetailSimilarMovies(
    pagingMoviesSimilar: LazyPagingItems<Movie>, modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        items(pagingMoviesSimilar.itemCount) { index ->
            val movie = pagingMoviesSimilar[index]
            movie?.let { movie ->
                MovieItem(
                    id = movie.id,
                    voteAverage = movie.voteAverage,
                    imageUrl = movie.imageUrl,
                    onClick = {},
                )
            }
        }
        pagingMoviesSimilar.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        LoadingView()
                    }
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        ErrorScreen(message = "Verifique sua conexão com a internet") {
                            retry()
                        }
                    }

                }
            }

        }

    }

}