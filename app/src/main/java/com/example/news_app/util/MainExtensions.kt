package com.example.news_app.util

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadUrl(url: String){
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(this)
}