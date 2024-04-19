package com.example.movieapi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapi.data.Result
import com.example.movieapi.data.repo.MovieApi
import retrofit2.HttpException
import java.io.IOException

class AllMoviePaging (
    private val api: MovieApi
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1

            val response = api.getAllMovie(currentPage.toString())
            val data = if (response.isSuccessful) response.body()!!.results
            else throw HttpException(response)

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (currentPage != 50) currentPage.plus(1) else null,
            )

        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
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