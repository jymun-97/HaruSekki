package com.jymun.harusekki.ui.extensions

import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addSnapToCenterHelper() {
    clipToPadding = false
    clipChildren = false
    PagerSnapHelper().attachToRecyclerView(this)
}

fun RecyclerView.addSnapToStartHelper() {
    clipToPadding = false
    clipChildren = false
    SnapToStartHelper().attachToRecyclerView(this)
}