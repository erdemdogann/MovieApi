package com.example.movieapi.presentation.allmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapi.data.api.MovieApi
import com.example.movieapi.data.paging.allmovie.AllMoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllMovieViewModel @Inject constructor(
    private val api: MovieApi
) : ViewModel() {

    val movies = Pager(
        PagingConfig(pageSize = 20)
    ) {
        AllMoviePagingSource(api)
    }.flow
        .cachedIn(viewModelScope)
}