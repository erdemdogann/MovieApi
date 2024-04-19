package com.example.movieapi

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.data.Result
import com.example.movieapi.databinding.MovieCardBinding

class Holder(val itemBinding: MovieCardBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(movie: Result) {
        itemBinding.apply {
            movieName.text = movie.title
            movieImage.loadImage(movie.poster_path)
        }
    }
}
