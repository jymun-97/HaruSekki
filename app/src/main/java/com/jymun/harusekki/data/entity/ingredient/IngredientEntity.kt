package com.jymun.harusekki.data.entity.ingredient

import androidx.room.PrimaryKey
import com.jymun.harusekki.data.entity.Entity

@androidx.room.Entity(
    tableName = "ingredient"
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = false)
    override val id: Long,
    val title: String,
    val category: String,
    val image: String?
) : Entity