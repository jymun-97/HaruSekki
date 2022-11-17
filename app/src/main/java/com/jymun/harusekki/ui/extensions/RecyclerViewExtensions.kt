package com.jymun.harusekki.ui.extensions

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addSnapHelper() {
    LinearSnapHelper().attachToRecyclerView(this)
}