package com.jymun.harusekki.data.model.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class Recipe(
    override val id: Long,
    override val type: ModelType = ModelType.RECIPE,
    val title: String,
    val category: String,
    val summary: String,
    val hits: Long,
    val likes: Long,
    val imgList: List<String>
) : Model(id, type) {

    fun toEntity() = RecipeEntity(
        id = id,
        title = title,
        category = category,
        summary = summary,
        imgList = imgList,
        hits = hits,
        likes = likes,
        ingredientList = null,
        cookingStepList = null
    )
}