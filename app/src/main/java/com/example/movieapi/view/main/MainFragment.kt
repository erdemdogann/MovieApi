package com.example.movieapi.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movieapi.R
import com.example.movieapi.data.MovieData
import com.example.movieapi.data.repo.MovieApi
import com.example.movieapi.databinding.FragmentMainBinding
import com.example.movieapi.view.adapter.MainMovieCardAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {


    lateinit var binding: FragmentMainBinding

    //lateinit var viewModel: MainViewModel
    private var adapter: MainMovieCardAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        adapter = MainMovieCardAdapter()
        binding.newMovie.adapter = adapter

        binding.seeAll.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.seeAllMovies())
        }

        return binding.root
    }

}