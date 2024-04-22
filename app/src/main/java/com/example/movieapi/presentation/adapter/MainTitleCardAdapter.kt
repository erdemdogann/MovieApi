package com.example.movieapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.databinding.TitleCardBinding

class MainTitleCardAdapter(
    val title: String
) : RecyclerView.Adapter<MainTitleCardAdapter.Holder>() {

    var onClick: () -> Unit = {}

    inner class Holder(private val binding: TitleCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.titleName.text = title
            binding.seeAll.text = "See All ->"
        }

        init {
            binding.seeAll.setOnClickListener {
                onClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTitleCardAdapter.Holder {
        val binding = TitleCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: MainTitleCardAdapter.Holder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}