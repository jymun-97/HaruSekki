package com.jymun.harusekki.ui.home.recipe

import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.ui.base.adapter.AdapterListener

interface RecipeAdapterListener : AdapterListener {

    fun onRecipeItemClicked(recipe: Recipe)
}