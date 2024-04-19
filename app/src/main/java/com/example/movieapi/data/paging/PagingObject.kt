package com.example.movieapi.data.paging

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapi.data.Result

object PagingObject : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}