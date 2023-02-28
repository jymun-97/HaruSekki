package com.jymun.harusekki.data.source.ingredient

import com.jymun.harusekki.data.entity.ingredient.IngredientEntity
import com.jymun.harusekki.data.service.SearchRecipeService
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IngredientRemoteDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val searchIngredientService: SearchRecipeService
) : IngredientDateSource.Remote {

    override suspend fun loadAll(): List<IngredientEntity> = withContext(dispatcherProvider.io) {

        return@withContext searchIngredientService.loadAllIngredient().body()!!
    }

    override suspend fun searchByTitle(
        title: String
    ): List<IngredientEntity> = withContext(dispatcherProvider.io) {

        return@withContext searchIngredientService.searchIngredientByTitle(title).body()!!
    }

    override suspend fun searchByCategory(
        category: String
    ): List<IngredientEntity> = withContext(dispatcherProvider.io) {

        return@withContext searchIngredientService.searchIngredientByCategory(category).body()!!
    }
}