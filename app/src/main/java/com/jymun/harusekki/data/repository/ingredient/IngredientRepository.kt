package com.jymun.harusekki.data.repository.ingredient

import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

interface IngredientRepository {

    suspend fun searchAll(): List<IngredientEntity>

    suspend fun searchByTitle(title: String): List<IngredientEntity>

    suspend fun searchByCategory(category: String): List<IngredientEntity>

    suspend fun loadIngredientsInRefrigerator(): List<IngredientEntity>

    suspend fun addIngredientInRefrigerator(ingredientEntity: IngredientEntity)

    suspend fun deleteIngredientInRefrigerator(ingredientEntity: IngredientEntity)
}