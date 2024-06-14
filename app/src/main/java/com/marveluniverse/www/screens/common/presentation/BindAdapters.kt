package com.marveluniverse.www.screens.common.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?){
    Picasso.get().load(url).into(imageView)
}