package com.jymun.harusekki.data.db.ingredient

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

@Database(
    entities = [IngredientEntity::class],
    version = 1
)
abstract class IngredientDatabase : RoomDatabase() {

    abstract fun dao(): IngredientDao
}