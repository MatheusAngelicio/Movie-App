package br.com.movieapp.movie_detail.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_detail.data.repository.MovieDetailsRepositoryImpl
import br.com.movieapp.movie_detail.data.source.MovieDetailsRemoteDataSourceImpl
import br.com.movieapp.movie_detail.domain.repository.MovieDetailsRepository
import br.com.movieapp.movie_detail.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_detail.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource{
        return MovieDetailsRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMoveiDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository{
        return MovieDetailsRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsUseCase(movieDetailsRepository: MovieDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository = movieDetailsRepository)
    }


}