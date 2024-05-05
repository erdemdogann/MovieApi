package com.example.movieapi.presentation.main

import com.example.movieapi.data.dto.movie.Result

data class MainScreenState(
    val movieResult: List<String> = emptyList(),
    val seriesResult: List<String> = emptyList(),
    val allMovie: List<Result> = emptyList()
)
