package com.jymun.harusekki.data.source.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity

interface RecipeDataSource {

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