package com.harusekki.jymun.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @BindingAdapter("imageSrc")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: Any) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }

    @BindingAdapter("textColor")
    @JvmStatic
    fun setTextColor(textView: TextView, color: Int) {
        textView.setTextColor(ContextCompat.getColor(textView.context, color))
    }
}