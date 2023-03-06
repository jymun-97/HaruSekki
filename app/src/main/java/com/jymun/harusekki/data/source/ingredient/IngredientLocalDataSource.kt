package com.jymun.harusekki.data.source.ingredient

import com.jymun.harusekki.data.db.ingredient.IngredientDatabase
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IngredientLocalDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val db: IngredientDatabase
) : IngredientDateSource.Local {

    override suspend fun loadAll(): List<IngredientEntity> = withContext(dispatcherProvider.io) {

        return@withContext db.dao().loadIngredient()
    }

    override suspend fun insertIngredient(
        ingredientEntity: IngredientEntity
    ) = withContext(dispatcherProvider.io) {

        db.dao().insertIngredient(ingredientEntity)
    }

    override suspend fun deleteIngredient(
        ingredientEntity: IngredientEntity
    ) = withContext(dispatcherProvider.io) {

        db.dao().deleteIngredient(ingredientEntity)
    }

}