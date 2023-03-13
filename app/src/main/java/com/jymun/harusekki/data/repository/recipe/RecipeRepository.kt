package com.jymun.harusekki.data.repository.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity

interface RecipeRepository {

    suspend fun loadAll(
        orderBy: String,
        refreshFlag: Boolean = false
    ): List<RecipeEntity>

    suspend fun searchByTitle(
        title: String,
        orderBy: String,
        refreshFlag: Boolean = false
    ): List<RecipeEntity>

    suspend fun searchByCategory(
        category: String,
        orderBy: String,
        refreshFlag: Boolean = false
    ): List<RecipeEntity>

    suspend fun loadDetail(
        id: Long,
        refreshFlag: Boolean = false
    ): RecipeEntity

    suspend fun searchByIngredient(
        ingredientList: List<Long>
    ): List<RecipeEntity>

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