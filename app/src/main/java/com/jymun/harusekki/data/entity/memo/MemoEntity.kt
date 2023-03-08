package com.jymun.harusekki.data.entity.memo

import com.jymun.harusekki.data.entity.Entity
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

@androidx.room.Entity(
    tableName = "memo"
)
data class MemoEntity(
    override val id: Long,
    val text: String,
    val ingredient: IngredientEntity? = null,
    val isChecked: Boolean = false
) : Entity