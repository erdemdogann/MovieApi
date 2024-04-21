package com.example.movieapi.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapi.R
import com.example.movieapi.databinding.FragmentMainBinding
import com.example.movieapi.presentation.adapter.MainMovieAdapter
import com.example.movieapi.presentation.adapter.MainSeriesAdapter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    private val movieAdapter = MainMovieAdapter()
    private val seriesAdapter = MainSeriesAdapter()
    private val concatAdapter = ConcatAdapter(movieAdapter, seriesAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupAdapters()
        setupObserver()
    }

    private fun setupAdapters() {
        binding.newMovie.apply {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mainState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                movieAdapter.posterPathList = it.movieResult
                seriesAdapter.posterPathList = it.seriesResult
            }

            /*repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.movieState.collect{
                        adapter?.movieList = it.model!!
                    }
                }
            }*/
        }
    }

    private fun setupListener() {
        binding.seeAll.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.seeAllMovies())
        }
    }
}