package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_popular.data.mapper.toMovie
import br.com.movieapp.search_movie.domain.source.MovieSearchRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // boilerplate
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            // na primeira vez que for carregar, o params.key vai ser null entao eu vou pedir a pagina 1
            val pageNumber = params.key ?: 1

            // aqui é onde eu chamo o endpoint usando a pagina atual e a query
            val response = remoteDataSource.getSearchMovies(pageNumber, query)

            // aqui to extraindo a lista de filmes como resposta da api
            val movies = response.results

            // LoadResult.Page ele contem a lista paginada pra mim, e a chave da paginacao anterior e da paginacao seguinte
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

    companion object {
        private const val LIMIT = 20
    }


}