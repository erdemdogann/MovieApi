package com.example.movieapi

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieapi.data.api.MovieApi

fun ImageView.loadImage(movieImagePath: String?) {
    val apiImageBaseUrl = "${MovieApi.IMAGE_BASE_URL}$movieImagePath"
    Glide.with(this).load(apiImageBaseUrl).into(this)
}