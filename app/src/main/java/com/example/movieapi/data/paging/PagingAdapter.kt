package com.example.movieapi.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.movieapi.Holder
import com.example.movieapi.data.Result
import com.example.movieapi.databinding.MovieCardBinding


class PagingAdapter(diffCallback: DiffUtil.ItemCallback<Result>) :
    PagingDataAdapter<Result, Holder>(diffCallback) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

}