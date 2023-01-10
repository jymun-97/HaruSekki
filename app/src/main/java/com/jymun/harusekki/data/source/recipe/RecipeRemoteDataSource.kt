package com.jymun.harusekki.data.source.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.service.SearchRecipeService
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRemoteDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val searchRecipeService: SearchRecipeService
) : RecipeDataSource.Remote {

    override suspend fun loadAll(
        orderBy: String
    ) = withContext(dispatcherProvider.io) {

        return@withContext searchRecipeService.loadAll(orderBy).body()!!
    }

    override suspend fun searchByTitle(
        title: String,
        orderBy: String
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext searchRecipeService.searchByTitle(title, orderBy).body()!!
    }

    override suspend fun searchByCategory(
        category: String,
        orderBy: String
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext searchRecipeService.searchByCategory(category, orderBy).body()!!
    }

    override suspend fun loadDetail(
        id: Long
    ): RecipeEntity = withContext(dispatcherProvider.io) {

        return@withContext searchRecipeService.loadDetail(id).body()!!
    }

    override suspend fun searchByIngredient(
        ingredientList: List<Long>
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext searchRecipeService.searchByIngredient(ingredientList).body()!!
    }
}