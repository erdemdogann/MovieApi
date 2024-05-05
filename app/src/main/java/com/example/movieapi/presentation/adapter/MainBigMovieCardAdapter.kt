package com.example.movieapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.data.dto.movie.Result
import com.example.movieapi.databinding.MainMovieCardBinding
import com.example.movieapi.loadImage

class MainBigMovieCardAdapter() : RecyclerView.Adapter<MainBigMovieCardAdapter.Holder>() {

    var posterPathList: List<Result> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class Holder(private val binding: MainMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(posterPath: Result) {
            binding.apply {
                movieImage.loadImage(posterPath.poster_path)
                movieName.text = posterPath.title
                movieType.text = posterPath.original_language
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val binding =
            MainMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(posterPathList[position])
    }

    override fun getItemCount(): Int {
        return posterPathList.size
    }
}