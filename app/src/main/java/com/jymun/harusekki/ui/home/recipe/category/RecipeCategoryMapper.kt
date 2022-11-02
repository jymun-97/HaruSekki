package com.jymun.harusekki.ui.home.recipe.category

object RecipeCategoryMapper {

    private val categoryList = RecipeCategory.values()

    fun map(value: String): RecipeCategory = categoryList.find { value.contains(it.value) }!!
}