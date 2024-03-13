package br.com.movieapp.movie_popular.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_popular.data.repository.MoviePopularRepositoryImpl
import br.com.movieapp.movie_popular.data.source.MoviePopularRemoteDataSourceImpl
import br.com.movieapp.movie_popular.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular.domain.source.MoviePopularRemoteDataSource
import br.com.movieapp.movie_popular.domain.usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popular.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// @InstallIn(SingletonComponent::class) -> significa que o componente estara disponivel durante toodo ciclo de vida do app
object MoviePopularModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource {
        return MoviePopularRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDatasource: MoviePopularRemoteDataSource): MoviePopularRepository {
        return MoviePopularRepositoryImpl(remoteDatasource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: MoviePopularRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(moviePopularRepository)

    }
}