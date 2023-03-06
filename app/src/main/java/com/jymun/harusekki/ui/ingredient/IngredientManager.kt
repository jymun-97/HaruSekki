package com.jymun.harusekki.ui.ingredient

import com.jymun.harusekki.data.model.ingredient.Ingredient

object IngredientManager {

    private val selectedIngredientSet = hashSetOf<Ingredient>()

    fun isSelected(ingredient: Ingredient) = selectedIngredientSet.contains(ingredient)

    fun getSelectedIngredients() = selectedIngredientSet

    fun addIngredient(ingredient: Ingredient) = selectedIngredientSet.add(ingredient)

    fun deleteIngredient(ingredient: Ingredient) = selectedIngredientSet.remove(ingredient)

    fun clear() = selectedIngredientSet.clear()
}