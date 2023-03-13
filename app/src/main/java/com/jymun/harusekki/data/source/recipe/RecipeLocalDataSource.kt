package com.jymun.harusekki.data.source.recipe

import com.jymun.harusekki.data.db.recipe.RecipeDatabase
import com.jymun.harusekki.data.entity.recipe.LatestReadRecipe
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeLocalDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val db: RecipeDatabase
) : RecipeDataSource.Local {

    override suspend fun loadLatestReadRecipe(): List<RecipeEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext db.dao().loadLatestReadRecipe().map { it.recipe }
        }

    override suspend fun insertLatestReadRecipe(
        recipeEntity: RecipeEntity
    ) = withContext(dispatcherProvider.io) {
        db.dao().insertLatestReadRecipe(LatestReadRecipe(recipeEntity))
    }

    override suspend fun deleteOldestReadRecipe() = withContext(dispatcherProvider.io) {
        db.dao().deleteOldestReadRecipe()
    }

    override suspend fun loadLatestFavoriteRecipe(): List<RecipeEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext db.dao().loadLatestFavoriteRecipe()
        }

    override suspend fun loadMostHitsFavoriteRecipe(): List<RecipeEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext db.dao().loadMostHitsFavoriteRecipe()
        }

    override suspend fun loadMostLikesFavoriteRecipe(): List<RecipeEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext db.dao().loadMostLikesFavoriteRecipe()
        }

    override suspend fun insertFavoriteRecipe(
        recipeEntity: RecipeEntity
    ) = withContext(dispatcherProvider.io) {

        db.dao().insertFavoriteRecipe(recipeEntity)
    }

    override suspend fun deleteFavoriteRecipe(
        recipeEntity: RecipeEntity
    ) = withContext(dispatcherProvider.io) {

        db.dao().deleteFavoriteRecipe(recipeEntity)
    }

    override suspend fun isRecipeLiked(id: Long) = withContext(dispatcherProvider.io) {

        return@withContext db.dao().isRecipeLiked(id)
    }
}