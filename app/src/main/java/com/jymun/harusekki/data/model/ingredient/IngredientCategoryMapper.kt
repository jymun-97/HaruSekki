package com.jymun.harusekki.data.model.ingredient

object IngredientCategoryMapper {

    private val categoryList = IngredientCategory.values()

    fun map(title: String): IngredientCategory =
        categoryList.find { it.text == title } ?: IngredientCategory.OTHERS
}