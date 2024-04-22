package com.example.movieapi.presentation.allmovie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapi.R
import com.example.movieapi.data.paging.allmovie.AllMoviePagingAdapter
import com.example.movieapi.databinding.FragmentAllmovieBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllMovieFragment : Fragment(R.layout.fragment_allmovie) {

    private val binding by viewBinding(FragmentAllmovieBinding::bind)
    private val viewModel by viewModels<AllMovieViewModel>()
    private val pagingAdapter = AllMoviePagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.movies.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupAdapter() {
        binding.allMovie.apply {
            adapter = pagingAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}