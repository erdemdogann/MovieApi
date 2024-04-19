package com.example.movieapi.data

data class MovieData(
    val page: Int?,
    val results: List<Result>,
    val total_pages: Int?,
    val total_results: Int?
)