package com.harusekki.jymun.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harusekki.jymun.data.model.TestModel
import com.harusekki.jymun.ui.adapter.MemoAdapter

object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: List<TestModel>?) {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = MemoAdapter()
        }
        (recyclerView.adapter as MemoAdapter).submitList(items?.toMutableList())
    }

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