package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.core.util.Constants
import br.com.movieapp.movie_detail.presentation.MovieDetailScreen
import br.com.movieapp.movie_detail.presentation.MovieDetailViewModel
import br.com.movieapp.movie_popular.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular.presentation.viewModel.MoviePopularViewModel
import br.com.movieapp.search_movie.presentation.MovieSearchEvent
import br.com.movieapp.search_movie.presentation.MovieSearchScreen
import br.com.movieapp.search_movie.presentation.viewModel.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {

            // hiltViewModel > para recuperar a instancia do viewModel que é criada e gerenciada pelo hilt
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                onClick = { id ->
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = id))
                }
            )

        }
        composable(BottomNavItem.MovieSearch.route) {

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch


            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = { id ->
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = id))
                },
            )

        }
        composable(BottomNavItem.MovieFavorite.route) {

        }


        composable(
            route = BottomNavItem.MovieDetail.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val getMovieDetail = viewModel::getMovieDetail
            MovieDetailScreen(
                id = it.arguments?.getInt(Constants.MOVIE_DETAIL_ARGUMENT_KEY),
                uiState = uiState,
                getMovieDetail = getMovieDetail
            )

        }

    }

}