package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.Constants.LIMIT_PAGE_MOVIE
import br.com.movieapp.movie_detail.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_popular.data.mapper.toMovie
import retrofit2.HttpException
import java.io.IOException

class MovieSimilarPagingSource(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val movieId: Int,
) : PagingSource<Int, Movie>( ){

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT_PAGE_MOVIE) ?: anchorPage?.nextKey?.minus(
                LIMIT_PAGE_MOVIE
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {

            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getMoviesSimilar(pageNumber, movieId)
            val movies = response.results

            LoadResult.Page(
                data = movies.toMovie(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )

        } catch (e: IOException) {
            e.printStackTrace()
            return LoadResult.Error(e)

        } catch (e: HttpException) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}