package com.jymun.harusekki.data.source.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity

interface RecipeDataSource {

    interface Remote {

        suspend fun loadAll(
            orderBy: String
        ): List<RecipeEntity>

        suspend fun searchByTitle(
            title: String,
            orderBy: String
        ): List<RecipeEntity>

        suspend fun searchByCategory(
            category: String,
            orderBy: String
        ): List<RecipeEntity>

        suspend fun loadDetail(
            id: Long
        ): RecipeEntity

        suspend fun searchByIngredient(
            ingredientList: List<Long>
        ): List<RecipeEntity>
    }

    interface Local {

        suspend fun loadLatestReadRecipe(): List<RecipeEntity>

        suspend fun insertLatestReadRecipe(recipeEntity: RecipeEntity)

        suspend fun deleteOldestReadRecipe()

        suspend fun loadLatestFavoriteRecipe(): List<RecipeEntity>

        suspend fun loadMostHitsFavoriteRecipe(): List<RecipeEntity>

        suspend fun loadMostLikesFavoriteRecipe(): List<RecipeEntity>

        suspend fun insertFavoriteRecipe(recipeEntity: RecipeEntity)

        suspend fun deleteFavoriteRecipe(recipeEntity: RecipeEntity)

        suspend fun isRecipeLiked(id: Long): Boolean
    }
}