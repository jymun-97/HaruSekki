package com.jymun.harusekki.data.model.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.cooking_step.CookingStep
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory

data class RecipeDetail(
    override val id: Long,
    override val type: ModelType = ModelType.RECIPE_LINEAR,
    val title: String,
    val category: RecipeCategory,
    val summary: String,
    val hits: Long,
    val likes: Long,
    val imgList: List<String>,
    val ingredientList: List<Ingredient>,
    val cookingStepList: List<CookingStep>
) : Model(id, type) {

    fun toEntity() = RecipeEntity(
        id = id,
        title = title,
        category = category.value,
        summary = summary,
        imgList = imgList,
        hits = hits,
        likes = likes,
        ingredientList = ingredientList.map { it.toEntity() },
        cookingStepList = cookingStepList.map { it.toEntity() }
    )
}