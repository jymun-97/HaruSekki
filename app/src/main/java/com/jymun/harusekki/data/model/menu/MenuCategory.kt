package com.jymun.harusekki.data.model.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jymun.harusekki.R

enum class MenuCategory(
    val id: Int,
    @StringRes val textResId: Int,
    @DrawableRes val iconResId: Int
) {
    BREAKFAST(0, R.string.breakfast, R.drawable.ic_breakfast),
    LUNCH(1, R.string.lunch, R.drawable.ic_lunch),
    DINNER(2, R.string.dinner, R.drawable.ic_dinner)
}