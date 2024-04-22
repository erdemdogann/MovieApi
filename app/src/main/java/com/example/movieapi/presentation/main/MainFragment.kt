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
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.movieapi.R
import com.example.movieapi.databinding.FragmentMainBinding
import com.example.movieapi.presentation.adapter.MainBigMovieCardAdapter
import com.example.movieapi.presentation.adapter.MainMovieAdapter
import com.example.movieapi.presentation.adapter.MainSeriesAdapter
import com.example.movieapi.presentation.adapter.MainTitleCardAdapter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    private val bigScreenAdapter = MainBigMovieCardAdapter()
    private val movieAdapter = MainMovieAdapter()
    private val seriesAdapter = MainSeriesAdapter()
    private var movieTitleAdapter = MainTitleCardAdapter("Movie")
    private var seriesTitleAdapter = MainTitleCardAdapter("Series")
    private val concatAdapter =
        ConcatAdapter(movieTitleAdapter, movieAdapter, seriesTitleAdapter, seriesAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupAdapters()
        setupObserver()
    }

    private fun setupAdapters() {
        binding.apply {
            newMovie.apply {
                adapter = concatAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            bigScreen.apply {
                adapter = bigScreenAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                PagerSnapHelper().attachToRecyclerView(this)
            }
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mainState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                movieAdapter.posterPathList = it.movieResult
                seriesAdapter.posterPathList = it.seriesResult
                bigScreenAdapter.posterPathList = it.movieResult
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

        movieTitleAdapter.onClick = {
            findNavController().navigate(MainFragmentDirections.seeAllMovies())
        }

        seriesTitleAdapter.onClick = {
            findNavController().navigate(MainFragmentDirections.seeAllSeries())
        }
    }
}