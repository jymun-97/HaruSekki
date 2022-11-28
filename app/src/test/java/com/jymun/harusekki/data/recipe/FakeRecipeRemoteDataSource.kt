package com.jymun.harusekki.data.recipe

import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.source.recipe.RecipeDataSource

class FakeRecipeRemoteDataSource(
    initList: List<RecipeEntity>
) : RecipeDataSource {

    private var recipeList: List<RecipeEntity> = initList

    override suspend fun loadAll(orderBy: String): List<RecipeEntity> {
        return recipeList
    }

    override suspend fun searchByTitle(title: String, orderBy: String): List<RecipeEntity> {
        return recipeList.filter { it.title.contains(title) }
    }

    override suspend fun searchByCategory(category: String, orderBy: String): List<RecipeEntity> {
        return recipeList.filter { it.category == category }
    }

    override suspend fun loadDetail(id: Long): RecipeEntity {
        return recipeList.find { it.id == id }!!
    }

    override suspend fun searchByIngredient(ingredientList: List<Long>): List<RecipeEntity> {
        return recipeList.filter { ingredientList.containsAll(it.ingredientList!!.map { it.id }) }
    }

    fun updateRecipeList(newList: List<RecipeEntity>) {
        recipeList = newList
    }
}