package com.example.movieapi.data.services

import com.example.movieapi.data.repo.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieService {

    val apiUrl = "https://api.themoviedb.org"


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingIntercepter = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder().addInterceptor(Interceptor {
            var newRequest = it.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMjMxNzAwMzk1NDNjNDI3ZjVkNTA1ZjRmNDYzY2Y1OSIsInN1YiI6IjY2MTI5ZTY4ZDc2OGZlMDE3YjQzZDYzMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.n7C4N4iQeUTwZuPRzYKwyCBI8-W10CZii4SUGZEU62c"
                ).build()

            it.proceed(newRequest)
        }).addInterceptor(loggingIntercepter).build()

    }

    @Provides
    @Singleton
    fun provideOnePieceApi(okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }

}