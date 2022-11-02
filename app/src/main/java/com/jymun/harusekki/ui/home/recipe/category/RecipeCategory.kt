package com.jymun.harusekki.ui.home.recipe.category

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jymun.harusekki.R

enum class RecipeCategory(
    val value: String,
    @StringRes val textStrId: Int,
    @DrawableRes val imageResId: Int
) {
    ALL("", R.string.recipe_category_all, R.drawable.ic_category_all),
    RICE("밥", R.string.recipe_category_rice, R.drawable.ic_category_rice),
    SIDE("반찬", R.string.recipe_category_side, R.drawable.ic_category_side),
    SOUP("국", R.string.recipe_category_soup, R.drawable.ic_category_soup),
    STIR("볶음", R.string.recipe_category_stir, R.drawable.ic_category_stir),
    NOODLE("면", R.string.recipe_category_noodle, R.drawable.ic_category_noodle),
    STEAMED("찜", R.string.recipe_category_steamed, R.drawable.ic_category_steamed),
    WESTERN("양식", R.string.recipe_category_western, R.drawable.ic_category_western),
    FRIED("튀김", R.string.recipe_category_fried, R.drawable.ic_category_fried),
    OTHERS("기타", R.string.recipe_category_others, R.drawable.ic_category_others),
}