package com.example.movieapi.data.api

import com.example.movieapi.data.dto.movie.MovieData
import com.example.movieapi.data.dto.series.SeriesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    companion object {
        const val API_URL = "https://api.themoviedb.org"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @GET("/3/discover/movie")
    suspend fun getAllMovie(
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "tr-TR",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<MovieData>

    @GET("/3/discover/tv")
    suspend fun getAllSeries(
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeFirstAirDates: Boolean = false,
        @Query("language") language: String = "tr-TR",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<SeriesData>
}