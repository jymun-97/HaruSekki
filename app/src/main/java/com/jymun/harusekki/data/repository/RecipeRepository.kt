package com.jymun.harusekki.data.repository

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

}