package com.jymun.harusekki.data.service

import com.jymun.harusekki.data.entity.recipe.RecipeDetailEntity
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.entity.recipe.SearchRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRecipeService {

    @GET("/recipe/findall")
    suspend fun loadAll(
        @Query("order") orderBy: String
    ): Response<SearchRecipeResponse>

    @GET("/recipe/findbytitle")
    suspend fun searchByTitle(
        @Query("recipe_title") title: String,
        @Query("order") orderBy: String
    ): Response<List<RecipeEntity>>

    @GET("/recipe/findbycategory")
    suspend fun searchByCategory(
        @Query("recipe_category") category: String,
        @Query("order") orderBy: String
    ): Response<List<RecipeEntity>>

    @GET("/recipe/detail")
    suspend fun loadDetail(
        @Query("recipe_id") id: Long
    ): Response<RecipeDetailEntity>

    @GET("/recipe/findbyingredientlist")
    suspend fun searchByIngredient(
        @Query("ingredients") ingredientList: List<Long>
    ): Response<List<RecipeEntity>>
}