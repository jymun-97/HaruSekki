package com.jymun.harusekki.data.db.recipe

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jymun.harusekki.data.entity.recipe.LatestReadRecipe
import com.jymun.harusekki.data.entity.recipe.RecipeEntity

@Database(
    entities = [LatestReadRecipe::class, RecipeEntity::class],
    version = 1
)
@TypeConverters(RecipeTypeConverters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun dao(): RecipeDao
}