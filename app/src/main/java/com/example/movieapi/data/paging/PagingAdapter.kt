package com.example.movieapi.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.data.dto.movie.Result
import com.example.movieapi.databinding.MovieCardBinding
import com.example.movieapi.loadImage

class PagingAdapter : PagingDataAdapter<Result, PagingAdapter.Holder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    class Holder(private val binding: MovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Result?) {
            binding.apply {
                movieName.text = movie?.title
                movieImage.loadImage(movie?.poster_path)
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