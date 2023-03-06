package com.jymun.harusekki.data.source.ingredient

import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

interface IngredientDateSource {

    interface Remote {
        suspend fun loadAll(): List<IngredientEntity>

        suspend fun searchByTitle(title: String): List<IngredientEntity>

        suspend fun searchByCategory(category: String): List<IngredientEntity>
    }
    
    interface Local {
        suspend fun loadAll(): List<IngredientEntity>

        suspend fun insertIngredient(ingredientEntity: IngredientEntity)

        suspend fun deleteIngredient(ingredientEntity: IngredientEntity)
    }
}