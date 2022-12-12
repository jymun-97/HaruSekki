package com.jymun.harusekki.ui.extensions

import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter

object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setRecyclerviewItems(
        recyclerView: RecyclerView,
        items: List<Model>?
    ) {
        items ?: return
        (recyclerView.adapter as ModelRecyclerAdapter<*>).submitList(items.toMutableList())
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(
        imageView: ImageView,
        imageUrl: String?
    ) {
        val animPlaceholder = ContextCompat.getDrawable(
            imageView.context,
            R.drawable.anim_loading
        ) as AnimationDrawable
        animPlaceholder.start()

        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(animPlaceholder)
            .error(R.drawable.ic_load_fail)
            .fallback(R.drawable.ic_load_fail)
            .into(imageView)
    }
}