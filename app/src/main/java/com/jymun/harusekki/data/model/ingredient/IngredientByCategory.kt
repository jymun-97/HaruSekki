package com.jymun.harusekki.data.model.ingredient

import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class IngredientByCategory(
    override val id: Long,
    override val type: ModelType = ModelType.INGREDIENT_BY_CATEGORY,
    val category: IngredientCategory,
    val ingredientList: List<Ingredient>
) : Model(id, type)