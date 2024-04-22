package com.example.movieapi.presentation.allserie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapi.data.api.MovieApi
import com.example.movieapi.data.paging.allseries.AllSeriesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllSerieViewModel @Inject constructor(
    private val api: MovieApi
) : ViewModel() {
    val series = Pager(
        PagingConfig(pageSize = 20)
    ) {
        AllSeriesPagingSource(api)
    }.flow
        .cachedIn(viewModelScope)
}