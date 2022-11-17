package com.jymun.harusekki.ui.home.recipe.category

import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.recipe.RecipeCategory

object RecipeCategoryGenerator {
    private val recipeCategoryList: List<RecipeCategory> by lazy {
        listOf(
            RecipeCategory(
                id = 0,
                name = RecipeCategoryType.RICE.value,
                imgResId = R.drawable.ic_category_rice
            ),
            RecipeCategory(
                id = 1,
                name = RecipeCategoryType.SIDE.value,
                imgResId = R.drawable.ic_category_side
            ),
            RecipeCategory(
                id = 2,
                name = RecipeCategoryType.SOUP.value,
                imgResId = R.drawable.ic_category_soup
            ),
            RecipeCategory(
                id = 3,
                name = RecipeCategoryType.STIR.value,
                imgResId = R.drawable.ic_category_stir
            ),
            RecipeCategory(
                id = 4,
                name = RecipeCategoryType.NOODLE.value,
                imgResId = R.drawable.ic_category_noodle
            ),
            RecipeCategory(
                id = 5,
                name = RecipeCategoryType.STEAMED.value,
                imgResId = R.drawable.ic_category_steamed
            ),
            RecipeCategory(
                id = 6,
                name = RecipeCategoryType.WESTERN.value,
                imgResId = R.drawable.ic_category_western
            ),
            RecipeCategory(
                id = 7,
                name = RecipeCategoryType.FRIED.value,
                imgResId = R.drawable.ic_category_fried
            ),
            RecipeCategory(
                id = 8,
                name = RecipeCategoryType.OTHERS.value,
                imgResId = R.drawable.ic_category_others
            ),
        )
    }

    fun get() = recipeCategoryList
}