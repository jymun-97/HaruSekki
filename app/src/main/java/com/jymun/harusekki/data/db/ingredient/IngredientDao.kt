package com.jymun.harusekki.data.db.ingredient

import androidx.room.*
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient")
    suspend fun loadIngredient(): List<IngredientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredientEntity: IngredientEntity)

    @Delete
    suspend fun deleteIngredient(ingredientEntity: IngredientEntity)
}