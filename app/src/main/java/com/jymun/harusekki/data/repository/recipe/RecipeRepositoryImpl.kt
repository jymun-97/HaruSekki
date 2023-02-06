package com.jymun.harusekki.data.repository.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.source.recipe.RecipeDataSource
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeLocalDataSource: RecipeDataSource.Local,
    private val recipeRemoteDataSource: RecipeDataSource.Remote,
) : RecipeRepository {

    override suspend fun loadAll(
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext recipeRemoteDataSource.loadAll(orderBy)
    }

    override suspend fun searchByTitle(
        title: String,
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext recipeRemoteDataSource.searchByTitle(title, orderBy)
    }

    override suspend fun searchByCategory(
        category: String,
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext recipeRemoteDataSource.searchByCategory(category, orderBy)
    }

    override suspend fun loadDetail(
        id: Long,
        refreshFlag: Boolean
    ): RecipeEntity = withContext(dispatcherProvider.io) {

        return@withContext recipeRemoteDataSource.loadDetail(id)
    }

    override suspend fun searchByIngredient(
        ingredientList: List<Long>
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext recipeRemoteDataSource.searchByIngredient(ingredientList)
    }

    override suspend fun loadLatestReadRecipe(): List<RecipeEntity> =
        withContext(dispatcherProvider.io) {
            return@withContext recipeLocalDataSource.loadLatestReadRecipe()
        }

    override suspend fun insertLatestReadRecipe(
        recipeEntity: RecipeEntity
    ) = withContext(dispatcherProvider.io) {

        recipeLocalDataSource.insertLatestReadRecipe(recipeEntity)
    }

    override suspend fun deleteOldestReadRecipe() = withContext(dispatcherProvider.io) {
        recipeLocalDataSource.deleteOldestReadRecipe()
    }

}