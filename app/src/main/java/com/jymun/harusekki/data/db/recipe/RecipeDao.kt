package com.jymun.harusekki.data.db.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jymun.harusekki.data.entity.recipe.LatestReadRecipe

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
}