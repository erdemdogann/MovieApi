package com.example.movieapi.data.dto.series

data class SeriesData(
    val page: Int?,
    val results: List<SeriesResult>?,
    val total_pages: Int?,
    val total_results: Int?
)