package com.jymun.harusekki.data.model.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class Recipe(
    override val id: Long,
    override val type: ModelType = ModelType.RECIPE_LINEAR,
    val title: String,
    val category: RecipeCategory,
    val summary: String,
    val hits: Long,
    val likes: Long,
    val imgList: List<String>
) : Model(id, type) {

    fun toEntity() = RecipeEntity(
        id = id,
        title = title,
        category = category.name,
        summary = summary,
        imgList = imgList,
        hits = hits,
        likes = likes,
        ingredientList = null,
        cookingStepList = null
    )
}