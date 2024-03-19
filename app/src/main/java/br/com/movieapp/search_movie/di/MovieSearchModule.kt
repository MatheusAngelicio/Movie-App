package br.com.movieapp.search_movie.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.search_movie.data.repository.MovieSearchRepositoryImpl
import br.com.movieapp.search_movie.data.source.MovieSearchRemoteDataSourceImpl
import br.com.movieapp.search_movie.domain.repository.MovieSearchRepository
import br.com.movieapp.search_movie.domain.source.MovieSearchRemoteDataSource
import br.com.movieapp.search_movie.domain.usecase.GetMovieSearchUseCase
import br.com.movieapp.search_movie.domain.usecase.GetMovieSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchModule {

    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService): MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideMovieSearchUseCase(repository: MovieSearchRepository): GetMovieSearchUseCase {
        return GetMovieSearchUseCaseImpl(repository)
    }
}