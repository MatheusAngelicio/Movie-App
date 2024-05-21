package br.com.movieapp.movie_popular.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView

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

            pagingMovies.apply {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        item(
                            //GridItemSpan- explicação
//item(span = { GridItemSpan(maxLineSpan) }): Essa linha define um item na grade (grid) onde a visualização de carregamento será exibida.
//A função span é usada para especificar o número de colunas que o item ocupa na grade.
//No caso, GridItemSpan(maxLineSpan) retorna um objeto SpanSize, indicando que o item deve ocupar um número máximo de linhas (maxLineSpan) na grade.
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

}