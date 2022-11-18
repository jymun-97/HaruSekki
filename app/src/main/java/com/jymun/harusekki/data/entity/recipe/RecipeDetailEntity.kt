package com.jymun.harusekki.data.entity.recipe


import com.google.gson.annotations.SerializedName
import com.jymun.harusekki.data.entity.Entity
import com.jymun.harusekki.data.entity.cooking_step.CookingStepEntity
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

data class RecipeDetailEntity(
    override val id: Long,
    val title: String,
    val category: String,
    val summary: String,
    val imgList: List<String>,
    val hits: Long,
    val likes: Long,
    val ingredientList: List<IngredientEntity>,
    @SerializedName("csList")
    val cookingStepList: List<CookingStepEntity>,
) : Entity