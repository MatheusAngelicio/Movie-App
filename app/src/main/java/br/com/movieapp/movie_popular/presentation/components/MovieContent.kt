package br.com.movieapp.movie_popular.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    // () < o tipo, Unit < nao devolve nada, como uma funcao
    onClick: (id: Int) -> Unit
) {

    Box(
        modifier = modifier.background(Color.Black)
    ) {
        LazyVerticalGrid(
            // 3 filmes por coluna
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        id = it.id,
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        onClick = { movieId ->
                            onClick(movieId)
                        }
                    )
                }

            }

        }

    }

}