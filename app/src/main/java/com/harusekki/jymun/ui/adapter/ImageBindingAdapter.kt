package com.harusekki.jymun.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @BindingAdapter("imageSrc")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: Int) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}