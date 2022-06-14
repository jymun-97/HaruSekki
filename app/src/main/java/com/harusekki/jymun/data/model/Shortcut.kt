package com.harusekki.jymun.data.model

import com.harusekki.jymun.util.Destination

data class Shortcut(
    val title: String,
    val subTitle: String,
    val image: Int,
    val color: Int,
    val destination: Destination
)