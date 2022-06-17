package com.harusekki.jymun.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harusekki.jymun.data.model.TestModel

object ItemBindingAdapter {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: List<TestModel>?) {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = MemoAdapter()
        }
        (recyclerView.adapter as MemoAdapter).submitList(items?.toMutableList())
    }
}