package com.example.movieapi.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.data.MovieData
import com.example.movieapi.data.ShortMovieList
import com.example.movieapi.data.repo.MovieApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MovieApi
) : ViewModel() {

    private val movieState: MutableStateFlow<ShortMovieList> = MutableStateFlow(
        ShortMovieList()
    )

    fun loadMovie() {
        viewModelScope.launch {
            api.getAllMovie("5")
            movieState.update {

            }
        }
    }

}