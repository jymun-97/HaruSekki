package com.jymun.harusekki.data.model.ingredient

import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class IngredientCategory(
    override val id: Long,
    override val type: ModelType = ModelType.INGREDIENT_CATEGORY,
    val name: String,
    val imgResId: Int
) : Model(id, type)