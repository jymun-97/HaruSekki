package com.jymun.harusekki.data.entity.ingredient

import com.jymun.harusekki.data.entity.Entity

data class IngredientEntity(
    override val id: Long,
    val title: String,
    val category: String,
    val image: String?
) : Entity