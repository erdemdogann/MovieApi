package com.example.movieapi.presentation.allserie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapi.R
import com.example.movieapi.data.paging.allseries.AllSeriesPagingAdapter
import com.example.movieapi.databinding.FragmentAllSerieBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllSerieFragment : Fragment(R.layout.fragment_all_serie) {
    private val binding by viewBinding(FragmentAllSerieBinding::bind)
    private val viewModel by viewModels<AllSerieViewModel>()
    private val pagingAdapter = AllSeriesPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupAdapter()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.series.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupAdapter() {

        binding.apply {
            back.setOnClickListener {
                findNavController().navigate(AllSerieFragmentDirections.seriesBackMain())
            }

            mainTitle.text = "All Serie"

            allSeries.apply {
                adapter = pagingAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

}