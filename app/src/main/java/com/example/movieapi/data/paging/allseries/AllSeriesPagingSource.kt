package com.example.movieapi.data.paging.allseries

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapi.data.api.MovieApi
import com.example.movieapi.data.dto.series.SeriesResult
import retrofit2.HttpException


private const val MAX_PAGE = 500

class AllSeriesPagingSource(
    private val api: MovieApi
) : PagingSource<Int, SeriesResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesResult> {
        return try {
            val currentPage = params.key ?: 1

            val response = api.getAllSeries(currentPage)
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

    override fun getRefreshKey(state: PagingState<Int, SeriesResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}