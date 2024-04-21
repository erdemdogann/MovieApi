package com.example.movieapi.presentation.main

data class MainScreenState(
    val movieResult: List<String> = emptyList(),
    val seriesResult: List<String> = emptyList(),
)
