package com.jymun.harusekki.data.cache.recipe

import androidx.collection.LruCache
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.ui.home.recipe.RecipeSortBy
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeDataCacheImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : RecipeDataCache {

    private var curSorted = RecipeSortBy.LATEST
    private var cachedRecipeList: MutableList<RecipeEntity>? = null
    private val lruCacheOfRecipeDetail = LruCache<Long, RecipeEntity>(LRU_MAX_SIZE)

    override suspend fun loadAll(
        orderBy: String
    ): List<RecipeEntity>? = withContext(dispatcherProvider.default) {

        return@withContext if (cachedRecipeList == null) null else {
            if (curSorted.value != orderBy) {
                sort(orderBy)
            }
            cachedRecipeList
        }
    }

    override suspend fun searchByTitle(
        title: String,
        orderBy: String
    ): List<RecipeEntity>? = withContext(dispatcherProvider.default) {

        return@withContext if (cachedRecipeList == null) null else {
            if (curSorted.value != orderBy) {
                sort(orderBy)
            }
            cachedRecipeList!!.filter { it.title == title }
        }
    }

    override suspend fun searchByCategory(
        category: String,
        orderBy: String
    ): List<RecipeEntity>? = withContext(dispatcherProvider.default) {

        return@withContext if (cachedRecipeList == null) null else {
            if (curSorted.value != orderBy) {
                sort(orderBy)
            }
            cachedRecipeList!!.filter { it.category == category }
        }
    }

    override fun loadDetail(id: Long): RecipeEntity? = lruCacheOfRecipeDetail[id]

    override fun updateCache(recipeList: List<RecipeEntity>) {
        cachedRecipeList = recipeList.toMutableList()
    }

    override fun updateCache(recipeEntity: RecipeEntity) {
        lruCacheOfRecipeDetail.put(recipeEntity.id, recipeEntity)
    }

    override fun refreshRecipeList() {
        cachedRecipeList = null
    }

    private fun sort(orderBy: String) = when (orderBy) {
        RecipeSortBy.OLD.value -> {
            cachedRecipeList!!.sortBy { it.id }
            curSorted = RecipeSortBy.OLD
        }

        RecipeSortBy.LATEST.value -> {
            cachedRecipeList!!.sortBy { -it.id }
            curSorted = RecipeSortBy.LATEST
        }

        RecipeSortBy.HITS_ASC.value -> {
            cachedRecipeList!!.sortBy { it.hits }
            curSorted = RecipeSortBy.HITS_ASC
        }

        RecipeSortBy.HITS_DESC.value -> {
            cachedRecipeList!!.sortBy { -it.hits }
            curSorted = RecipeSortBy.HITS_DESC
        }

        RecipeSortBy.LIKES_ASC.value -> {
            cachedRecipeList!!.sortBy { it.likes }
            curSorted = RecipeSortBy.LIKES_ASC
        }

        RecipeSortBy.LIKES_DESC.value -> {
            cachedRecipeList!!.sortBy { -it.likes }
            curSorted = RecipeSortBy.LIKES_DESC
        }

        else -> Unit
    }

    companion object {
        private const val LRU_MAX_SIZE = 10
    }
}