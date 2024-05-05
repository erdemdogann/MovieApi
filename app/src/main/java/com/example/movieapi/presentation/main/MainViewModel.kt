package com.example.movieapi.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.data.api.MovieApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val FIRST_PAGE = 1

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MovieApi
) : ViewModel() {
    init {
        fetchScreenData()
    }

    private val _mainState: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState())
    val mainState = _mainState.asStateFlow()

    private fun fetchScreenData() {
        viewModelScope.launch {
            val resultsMovie = async { api.getAllMovie(FIRST_PAGE).body()?.results }
            val resultSeries = async { api.getAllSeries(FIRST_PAGE).body()?.results }

            _mainState.update { state ->
                state.copy(
                    movieResult = resultsMovie.await()?.map { it.poster_path }.orEmpty(),
                    seriesResult = resultSeries.await()?.map { it.poster_path }.orEmpty(),
                    allMovie = resultsMovie.await().orEmpty()
                )
            }
        }
    }
}