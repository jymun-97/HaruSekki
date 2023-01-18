package com.jymun.harusekki.data.cache.recipe

import androidx.collection.LruCache
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class RecipeDataCacheImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : RecipeDataCache {

    private var curSorted = RecipeSortOption.LATEST
    private var cachedRecipeList: MutableList<RecipeEntity>? = null
    private val lruCacheOfRecipeDetail = LruCache<Long, RecipeEntity>(LRU_MAX_SIZE)
    private val latestReadRecipeList = LinkedList<RecipeEntity>()

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
            cachedRecipeList!!.filter { it.title.contains(title) }
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

    override fun initLatestReadRecipeList(latestReadRecipeList: List<RecipeEntity>) {
        this.latestReadRecipeList.addAll(latestReadRecipeList)
    }

    override fun loadLatestReadRecipe(): List<RecipeEntity> = latestReadRecipeList

    override fun insertLatestReadRecipe(recipeEntity: RecipeEntity) {
        latestReadRecipeList.removeIf { it.id == recipeEntity.id }
        latestReadRecipeList.addFirst(recipeEntity)
    }

    override fun deleteOldestReadRecipe() {
        if (latestReadRecipeList.size <= 10) return
        latestReadRecipeList.pollLast()
    }

    override fun refreshRecipeList() {
        cachedRecipeList = null
    }

    private fun sort(orderBy: String) = when (orderBy) {
        RecipeSortOption.OLD.value -> {
            cachedRecipeList!!.sortBy { -it.id }
            curSorted = RecipeSortOption.OLD
        }

        RecipeSortOption.LATEST.value -> {
            cachedRecipeList!!.sortBy { it.id }
            curSorted = RecipeSortOption.LATEST
        }

        RecipeSortOption.HITS_ASC.value -> {
            cachedRecipeList!!.sortBy { it.hits }
            curSorted = RecipeSortOption.HITS_ASC
        }

        RecipeSortOption.HITS_DESC.value -> {
            cachedRecipeList!!.sortBy { -it.hits }
            curSorted = RecipeSortOption.HITS_DESC
        }

        RecipeSortOption.LIKES_ASC.value -> {
            cachedRecipeList!!.sortBy { it.likes }
            curSorted = RecipeSortOption.LIKES_ASC
        }

        RecipeSortOption.LIKES_DESC.value -> {
            cachedRecipeList!!.sortBy { -it.likes }
            curSorted = RecipeSortOption.LIKES_DESC
        }

        else -> Unit
    }

    companion object {
        private const val LRU_MAX_SIZE = 10
    }
}