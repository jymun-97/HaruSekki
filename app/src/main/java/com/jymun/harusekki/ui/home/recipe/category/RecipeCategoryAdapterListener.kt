package com.jymun.harusekki.ui.home.recipe.category

import com.jymun.harusekki.data.model.recipe.RecipeCategory
import com.jymun.harusekki.ui.base.adapter.AdapterListener

interface RecipeCategoryAdapterListener : AdapterListener {

    fun onRecipeCategoryItemClicked(recipeCategory: RecipeCategory)
}