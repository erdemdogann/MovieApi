package com.example.movieapi.view.allmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapi.data.paging.PagingAdapter
import com.example.movieapi.data.paging.PagingObject
import com.example.movieapi.data.repo.MovieApi
import com.example.movieapi.databinding.FragmentAllmovieBinding
import com.example.movieapi.view.adapter.MainMovieCardAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AllMovieFragment : Fragment() {
    @Inject
    lateinit var allMovies: MovieApi

    private lateinit var binding: FragmentAllmovieBinding
    val viewModel by viewModels<AllMovieViewModel>()
    val pagingAdapter = PagingAdapter(PagingObject)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllmovieBinding.inflate(inflater, container, false)

        binding.allMovie.adapter = pagingAdapter
        binding.allMovie.layoutManager = GridLayoutManager(requireContext(),2)

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }

        return binding.root
    }


}