package com.example.movieapi.data.repo

import com.example.movieapi.data.MovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=popularity.desc")
    suspend fun getAllMovie(
        @Query("page") page:String
    ): Response<MovieData>
}