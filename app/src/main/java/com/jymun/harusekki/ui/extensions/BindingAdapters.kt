package com.jymun.harusekki.ui.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
}