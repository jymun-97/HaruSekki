package com.jymun.harusekki.domain.recipe

import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.data.repository.RecipeRepository
import com.jymun.harusekki.ui.home.recipe.RecipeSortBy
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.jymun.harusekki.util.exception.CustomExceptions
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRecipeByIngredientUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        ingredientList: List<Long>,
        orderBy: RecipeSortBy = RecipeSortBy.LATEST,
        refreshFlag: Boolean = false
    ): List<Recipe> = withContext(dispatcherProvider.default) {

        if (ingredientList.isEmpty()) throw CustomExceptions.NothingSelectedException()

        // TODO. api -> orderBy 파라미터 추가
        val recipeEntityList = recipeRepository.searchByIngredient(ingredientList)
        if (recipeEntityList.isEmpty()) throw CustomExceptions.NotDataExistException()

        return@withContext recipeEntityList.map {
            Recipe(
                id = it.id,
                type = ModelType.RECIPE_LINEAR,
                title = it.title,
                category = it.category,
                summary = it.summary,
                hits = it.hits,
                likes = it.likes,
                imgList = it.imgList
            )
        }
    }
}