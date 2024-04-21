package com.example.movieapi.presentation.adapter.holder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.databinding.ConcatCardBinding
import com.example.movieapi.presentation.adapter.PosterCardAdapter

class HorizontalCardHolder(private val binding: ConcatCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(posters: List<String>) {
        binding.concatRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            adapter = PosterCardAdapter().apply {
                posterPathList = posters
            }
        }
    }
}