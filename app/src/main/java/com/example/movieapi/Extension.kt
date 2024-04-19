package com.example.movieapi

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(movieImagePath:String){
val apiImageBaseUrl="https://image.tmdb.org/t/p/w500/$movieImagePath"
    Glide.with(this).load(apiImageBaseUrl).into(this)
}