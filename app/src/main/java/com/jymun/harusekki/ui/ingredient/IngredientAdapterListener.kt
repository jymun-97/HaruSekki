package com.jymun.harusekki.ui.ingredient

import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.ui.base.adapter.AdapterListener

interface IngredientAdapterListener : AdapterListener {

    fun onIngredientItemClicked(ingredient: Ingredient)
}