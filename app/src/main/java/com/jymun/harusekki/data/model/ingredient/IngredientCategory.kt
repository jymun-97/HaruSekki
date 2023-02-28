package com.jymun.harusekki.data.model.ingredient

import androidx.annotation.DrawableRes
import com.jymun.harusekki.R

enum class IngredientCategory(
    val text: String,
    @DrawableRes val iconResId: Int
) {
    GRAIN("곡류", R.drawable.ic_ingredient_grain),
    MEAT("육류", R.drawable.ic_ingredient_meat),
    VEGETABLE("채소", R.drawable.ic_ingredient_vegetable),
    SEAFOOD("해물", R.drawable.ic_ingredient_seafood),
    SEASONING("조미료", R.drawable.ic_ingredient_seasoning),
    DAIRY_PRODUCT("유제품", R.drawable.ic_ingredient_dairy_products),
    FRUIT("과일", R.drawable.ic_ingredient_fruit),
    NUTS("견과류", R.drawable.ic_ingredient_nuts),
    OTHERS("기타", R.drawable.ic_ingredient_others),
}