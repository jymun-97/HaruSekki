package com.harusekki.jymun.data.model

data class Shortcut(
    val title: String,
    val subTitle: String,
    val image: Int,
    val destination: Destination
)

enum class Destination {
    MENU, MEMO, REFRIGERATOR
}