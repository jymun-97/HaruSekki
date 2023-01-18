package com.jymun.harusekki.ui.home.recipe

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jymun.harusekki.R

enum class RecipeSortOption(
    val value: String,
    @StringRes val textStrResId: Int,
    @DrawableRes val imageResId: Int
) {
    LATEST("latest", R.string.sort_by_latest, R.drawable.ic_latest),
    HITS("hitsdesc", R.string.sort_by_hits, R.drawable.ic_hit),
    LIKES("likedesc", R.string.sort_by_likes, R.drawable.ic_like)
}