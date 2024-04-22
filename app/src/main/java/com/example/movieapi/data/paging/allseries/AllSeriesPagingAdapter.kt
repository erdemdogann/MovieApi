package com.example.movieapi.data.paging.allseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.data.dto.series.SeriesResult
import com.example.movieapi.databinding.MovieCardBinding
import com.example.movieapi.loadImage

class AllSeriesPagingAdapter :
    PagingDataAdapter<SeriesResult, AllSeriesPagingAdapter.Holder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<SeriesResult>() {
            override fun areItemsTheSame(oldItem: SeriesResult, newItem: SeriesResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SeriesResult, newItem: SeriesResult): Boolean {
                return oldItem == newItem
            }
        }
    }

    class Holder(private val binding: MovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesResult?) {
            binding.apply {
                movieName.text = series?.name
                movieImage.loadImage(series?.poster_path)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

}