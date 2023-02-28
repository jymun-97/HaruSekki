package com.jymun.harusekki.data.model.ingredient

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jymun.harusekki.R

enum class IngredientCategory(
    val id: Int,
    @StringRes val textResId: Int,
    @DrawableRes val iconResId: Int
) {
    GRAIN(0, R.string.grain, R.drawable.ic_ingredient_grain),
    MEAT(1, R.string.meat, R.drawable.ic_ingredient_meat),
    VEGETABLE(2, R.string.vegetable, R.drawable.ic_ingredient_vegetable),
    SEAFOOD(3, R.string.seafood, R.drawable.ic_ingredient_seafood),
    SEASONING(4, R.string.seasoning, R.drawable.ic_ingredient_seasoning),
    DAIRY_PRODUCT(5, R.string.dairy_product, R.drawable.ic_ingredient_dairy_products),
    FRUIT(6, R.string.fruit, R.drawable.ic_ingredient_fruit),
    NUTS(7, R.string.nuts, R.drawable.ic_ingredient_nuts),
    OTHERS(8, R.string.others, R.drawable.ic_ingredient_others),
}