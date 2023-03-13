package com.jymun.harusekki.data.service

import com.jymun.harusekki.data.entity.ingredient.IngredientEntity
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRecipeService {

    @GET("/recipe/findall")
    suspend fun loadAll(
        @Query("order") orderBy: String
    ): Response<List<RecipeEntity>>

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
    ): Response<RecipeEntity>

    @GET("/recipe/findbyingredients")
    suspend fun searchByIngredient(
        @Query("ingredients") ingredientList: List<Long>
    ): Response<List<RecipeEntity>>

    @GET("/ingredient/findall")
    suspend fun loadAllIngredient(): Response<List<IngredientEntity>>

    @GET("/ingredient/findbytitle")
    suspend fun searchIngredientByTitle(
        @Query("title") title: String
    ): Response<List<IngredientEntity>>

    @GET("/ingredient/findbycategory")
    suspend fun searchIngredientByCategory(
        @Query("ingredient_category") category: String
    ): Response<List<IngredientEntity>>
}