package com.example.movieapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.data.dto.series.SeriesResult
import com.example.movieapi.databinding.SeriesCardBinding
import com.example.movieapi.loadImage

class MainSeriesCardAdapter : RecyclerView.Adapter<MainSeriesCardAdapter.Holder>() {

    var seriesList: List<SeriesResult> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(val itemBinding: SeriesCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(series: SeriesResult) {
            itemBinding.seriesImage.loadImage(series.poster_path)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainSeriesCardAdapter.Holder {
        val binding = SeriesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: MainSeriesCardAdapter.Holder, position: Int) {
        holder.bind(seriesList[position])
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }
}