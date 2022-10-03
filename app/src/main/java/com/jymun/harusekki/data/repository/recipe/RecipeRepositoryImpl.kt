package com.jymun.harusekki.data.repository.recipe

import com.jymun.harusekki.data.cache.recipe.RecipeDataCache
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.source.recipe.RecipeDataSource
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeLocalDataSource: RecipeDataSource.Local,
    private val recipeRemoteDataSource: RecipeDataSource.Remote,
    private val recipeDataCache: RecipeDataCache
) : RecipeRepository {

    override suspend fun loadAll(
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext if (refreshFlag) {
            recipeRemoteDataSource.loadAll(orderBy).also { recipeDataCache.updateCache(it) }
        } else {
            recipeDataCache.loadAll(orderBy)
                ?: recipeRemoteDataSource.loadAll(orderBy).also {
                    recipeDataCache.updateCache(it)
                }
        }
    }

    override suspend fun searchByTitle(
        title: String,
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext if (refreshFlag) {
            recipeRemoteDataSource.searchByTitle(title, orderBy)
        } else {
            recipeDataCache.searchByTitle(title, orderBy)
                ?: recipeRemoteDataSource.searchByTitle(title, orderBy)
        }
    }

    override suspend fun searchByCategory(
        category: String,
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext if (refreshFlag) {
            recipeRemoteDataSource.searchByCategory(category, orderBy)
        } else {
            recipeDataCache.searchByCategory(category, orderBy)
                ?: recipeRemoteDataSource.searchByCategory(category, orderBy)
        }
    }

    override suspend fun loadDetail(
        id: Long,
        refreshFlag: Boolean
    ): RecipeEntity = withContext(dispatcherProvider.io) {

        return@withContext if (refreshFlag) {
            recipeRemoteDataSource.loadDetail(id).also { recipeDataCache.updateCache(it) }
        } else {
            recipeDataCache.loadDetail(id)
                ?: recipeRemoteDataSource.loadDetail(id).also {
                    recipeDataCache.updateCache(it)
                }
        }
    }

    override suspend fun searchByIngredient(
        ingredientList: List<Long>
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext recipeRemoteDataSource.searchByIngredient(ingredientList)
    }

    override suspend fun loadLatestReadRecipe(): List<RecipeEntity> =
        withContext(dispatcherProvider.io) {
            return@withContext recipeDataCache.loadLatestReadRecipe().ifEmpty {
                recipeLocalDataSource.loadLatestReadRecipe().also {
                    recipeDataCache.initLatestReadRecipeList(it)
                }
            }
        }

    override suspend fun insertLatestReadRecipe(
        recipeEntity: RecipeEntity
    ) = withContext(dispatcherProvider.io) {

        recipeLocalDataSource.insertLatestReadRecipe(recipeEntity)
        coroutineScope {
            if (recipeDataCache.loadLatestReadRecipe().isEmpty()) {
                recipeDataCache.initLatestReadRecipeList(
                    recipeLocalDataSource.loadLatestReadRecipe()
                )
            } else {
                recipeDataCache.insertLatestReadRecipe(recipeEntity)
            }
        }
    }

    override suspend fun deleteOldestReadRecipe() {
        recipeLocalDataSource.deleteOldestReadRecipe()
        coroutineScope {
            if (recipeDataCache.loadLatestReadRecipe().isEmpty()) {
                recipeDataCache.initLatestReadRecipeList(
                    recipeLocalDataSource.loadLatestReadRecipe()
                )
            } else {
                recipeDataCache.deleteOldestReadRecipe()
            }
        }
    }

}