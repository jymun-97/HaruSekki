package com.jymun.harusekki.data.repository.ingredient

import com.jymun.harusekki.data.entity.ingredient.IngredientEntity
import com.jymun.harusekki.data.source.ingredient.IngredientDateSource
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress
class IngredientRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val ingredientRemoteDataSource: IngredientDateSource.Remote,
    private val refrigerator: IngredientDateSource.Local
) : IngredientRepository {

    override suspend fun searchAll(): List<IngredientEntity> = withContext(dispatcherProvider.io) {

        return@withContext ingredientRemoteDataSource.loadAll()
    }

    override suspend fun searchByTitle(
        title: String
    ): List<IngredientEntity> = withContext(dispatcherProvider.io) {

        return@withContext ingredientRemoteDataSource.searchByTitle(title)
    }

    override suspend fun searchByCategory(
        category: String
    ): List<IngredientEntity> = withContext(dispatcherProvider.io) {

        return@withContext ingredientRemoteDataSource.searchByCategory(category)
    }

    override suspend fun loadIngredientsInRefrigerator(
        category: String
    ): List<IngredientEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext refrigerator.loadAll(category)
        }

    override suspend fun loadIngredientsInRefrigerator(): List<IngredientEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext refrigerator.loadAll()
        }

    override suspend fun addIngredientInRefrigerator(ingredientEntity: IngredientEntity) =
        withContext(dispatcherProvider.io) {

            refrigerator.insertIngredient(ingredientEntity)
        }

    override suspend fun deleteIngredientInRefrigerator(ingredientEntity: IngredientEntity) =
        withContext(dispatcherProvider.io) {

            refrigerator.deleteIngredient(ingredientEntity)
        }

}