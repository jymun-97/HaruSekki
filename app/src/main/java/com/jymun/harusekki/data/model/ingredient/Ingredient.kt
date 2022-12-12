package com.jymun.harusekki.data.model.ingredient

import com.jymun.harusekki.data.entity.ingredient.IngredientEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class Ingredient(
    override val id: Long,
    override val type: ModelType = ModelType.INGREDIENT,
    val title: String,
    val category: String,
    val image: String?
) : Model(id, type) {

    fun toEntity() = IngredientEntity(
        id = id,
        title = title,
        category = category,
        image = image
    )
}