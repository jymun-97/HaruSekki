package com.jymun.harusekki.domain.recipe

import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryMapper
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.jymun.harusekki.util.exception.CustomExceptions
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRecipeByTitleUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        keyword: String,
        orderBy: RecipeSortOption = RecipeSortOption.LATEST,
        category: RecipeCategory,
        refreshFlag: Boolean = false
    ): List<Recipe> = withContext(dispatcherProvider.default) {

        val recipeEntityList = recipeRepository.searchByTitle(keyword, orderBy.value, refreshFlag)
        if (recipeEntityList.isEmpty()) throw CustomExceptions.NotDataExistException()

        return@withContext recipeEntityList.map {
            Recipe(
                id = it.id,
                type = ModelType.RECIPE_LINEAR,
                title = it.title,
                category = RecipeCategoryMapper.map(it.category),
                summary = it.summary,
                hits = it.hits,
                likes = it.likes,
                imgList = it.imgList
            )
        }.filter {
            it.category == category || category == RecipeCategory.ALL
        }
    }
}