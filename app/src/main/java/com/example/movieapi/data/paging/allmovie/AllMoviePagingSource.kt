package com.example.movieapi.data.paging.allmovie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapi.data.api.MovieApi
import com.example.movieapi.data.dto.movie.Result
import retrofit2.HttpException

private const val MAX_PAGE = 500

class AllMoviePagingSource(
    private val api: MovieApi
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1

            val response = api.getAllMovie(currentPage)
            val data = if (response.isSuccessful) response.body()?.results.orEmpty()
            else throw HttpException(response)

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (currentPage != MAX_PAGE) currentPage.plus(1) else null,
            )

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}