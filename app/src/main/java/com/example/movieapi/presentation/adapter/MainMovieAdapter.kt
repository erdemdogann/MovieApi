package com.example.movieapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.databinding.ConcatCardBinding
import com.example.movieapi.presentation.adapter.holder.HorizontalCardHolder

class MainMovieAdapter : RecyclerView.Adapter<HorizontalCardHolder>() {

    var posterPathList: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalCardHolder {
        val binding = ConcatCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalCardHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalCardHolder, position: Int) {
        holder.bind(posterPathList)
    }

    override fun getItemCount(): Int = 1
}