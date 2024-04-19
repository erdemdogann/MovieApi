package com.example.movieapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.Holder
import com.example.movieapi.data.Result
import com.example.movieapi.databinding.MainCardBinding
import com.example.movieapi.databinding.MovieCardBinding
import com.example.movieapi.loadImage

class MainMovieCardAdapter : RecyclerView.Adapter<MainMovieCardAdapter.Holder>() {

    var movieList: List<Result> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(val itemBinding: MainCardBinding):RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Result) {

            itemBinding.movieImage.loadImage(movie.poster_path)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MainCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}