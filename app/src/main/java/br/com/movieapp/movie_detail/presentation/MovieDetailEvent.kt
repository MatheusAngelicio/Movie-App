package br.com.movieapp.movie_detail.presentation

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int): MovieDetailEvent()
}