package com.jymun.harusekki.data.model.recipe

import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class RecipeCategory(
    override val id: Long,
    override val type: ModelType = ModelType.RECIPE_CATEGORY,
    val name: String,
    val imgResId: Int
) : Model(id, type) {

    val text: String get() = name.replaceFirst('/', '\n')
}