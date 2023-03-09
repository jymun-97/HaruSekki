package com.jymun.harusekki.data.entity.memo

import androidx.room.PrimaryKey
import com.jymun.harusekki.data.entity.Entity
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

@androidx.room.Entity(
    tableName = "memo"
)
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    val text: String,
    val ingredient: IngredientEntity? = null,
    val isChecked: Boolean = false
) : Entity