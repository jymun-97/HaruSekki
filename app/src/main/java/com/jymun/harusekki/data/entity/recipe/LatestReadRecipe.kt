package com.jymun.harusekki.data.entity.recipe

import androidx.room.Embedded
import androidx.room.Entity
import java.time.LocalDateTime

@Entity(
    tableName = "latest_read_recipe",
    primaryKeys = ["id"]
)
data class LatestReadRecipe(
    @Embedded
    val recipe: RecipeEntity,
    val timestamp: String = LocalDateTime.now().toString()
)