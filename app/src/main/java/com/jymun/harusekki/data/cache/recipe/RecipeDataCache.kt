package com.jymun.harusekki.data.cache.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity

interface RecipeDataCache {

    suspend fun loadAll(
        orderBy: String
    ): List<RecipeEntity>?

    suspend fun searchByTitle(
        title: String,
        orderBy: String
    ): List<RecipeEntity>?

    suspend fun searchByCategory(
        category: String,
        orderBy: String
    ): List<RecipeEntity>?

    fun loadDetail(
        id: Long
    ): RecipeEntity?

    fun updateCache(
        recipeList: List<RecipeEntity>
    )

    fun updateCache(
        recipeEntity: RecipeEntity
    )

    fun initLatestReadRecipeList(latestReadRecipeList: List<RecipeEntity>)

    fun loadLatestReadRecipe(): List<RecipeEntity>

    fun insertLatestReadRecipe(recipeEntity: RecipeEntity)

    fun deleteOldestReadRecipe()

    fun refreshRecipeList()
}