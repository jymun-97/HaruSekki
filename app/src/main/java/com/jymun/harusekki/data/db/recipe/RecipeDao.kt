package com.jymun.harusekki.data.db.recipe

import androidx.room.*
import com.jymun.harusekki.data.entity.recipe.LatestReadRecipe
import com.jymun.harusekki.data.entity.recipe.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM latest_read_recipe ORDER BY timestamp DESC")
    suspend fun loadLatestReadRecipe(): List<LatestReadRecipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatestReadRecipe(recipe: LatestReadRecipe)

    @Query(
        "DELETE FROM latest_read_recipe " +
                "WHERE id NOT IN " +
                "(SELECT id FROM latest_read_recipe ORDER BY timestamp DESC LIMIT 10)"
    )
    suspend fun deleteOldestReadRecipe()

    @Query("SELECT * FROM favorite_recipe ORDER BY id")
    suspend fun loadLatestFavoriteRecipe(): List<RecipeEntity>

    @Query("SELECT * FROM favorite_recipe ORDER BY hits DESC")
    suspend fun loadMostHitsFavoriteRecipe(): List<RecipeEntity>

    @Query("SELECT * FROM favorite_recipe ORDER BY likes DESC")
    suspend fun loadMostLikesFavoriteRecipe(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteFavoriteRecipe(recipe: RecipeEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_recipe WHERE id = :id)")
    suspend fun isRecipeLiked(id: Long): Boolean
}