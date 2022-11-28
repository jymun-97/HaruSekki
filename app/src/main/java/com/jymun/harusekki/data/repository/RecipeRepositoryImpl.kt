package com.jymun.harusekki.data.repository

import com.jymun.harusekki.data.cache.recipe.RecipeDataCache
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.source.recipe.RecipeDataSource
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeDataSource: RecipeDataSource,
    private val recipeDataCache: RecipeDataCache
) : RecipeRepository {

    override suspend fun loadAll(
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext if (refreshFlag) {
            recipeDataSource.loadAll(orderBy).also { recipeDataCache.updateCache(it) }
        } else {
            recipeDataCache.loadAll(orderBy)
                ?: recipeDataSource.loadAll(orderBy).also {
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
            recipeDataSource.searchByTitle(title, orderBy)
        } else {
            recipeDataCache.searchByTitle(title, orderBy)
                ?: recipeDataSource.searchByTitle(title, orderBy)
        }
    }

    override suspend fun searchByCategory(
        category: String,
        orderBy: String,
        refreshFlag: Boolean
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext if (refreshFlag) {
            recipeDataSource.searchByCategory(category, orderBy)
        } else {
            recipeDataCache.searchByCategory(category, orderBy)
                ?: recipeDataSource.searchByCategory(category, orderBy)
        }
    }

    override suspend fun loadDetail(
        id: Long,
        refreshFlag: Boolean
    ): RecipeEntity = withContext(dispatcherProvider.io) {

        return@withContext if (refreshFlag) {
            recipeDataSource.loadDetail(id).also { recipeDataCache.updateCache(it) }
        } else {
            recipeDataCache.loadDetail(id)
                ?: recipeDataSource.loadDetail(id).also {
                    recipeDataCache.updateCache(it)
                }
        }
    }

    override suspend fun searchByIngredient(
        ingredientList: List<Long>
    ): List<RecipeEntity> = withContext(dispatcherProvider.io) {

        return@withContext recipeDataSource.searchByIngredient(ingredientList)
    }

}