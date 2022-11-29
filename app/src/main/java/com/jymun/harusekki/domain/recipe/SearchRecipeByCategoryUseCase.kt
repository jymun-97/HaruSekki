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
class SearchRecipeByCategoryUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        category: String,
        orderBy: RecipeSortBy = RecipeSortBy.LATEST,
        refreshFlag: Boolean = false
    ): List<Recipe> = withContext(dispatcherProvider.default) {

        val recipeEntityList =
            recipeRepository.searchByCategory(category, orderBy.value, refreshFlag)
        if (recipeEntityList.isEmpty()) throw CustomExceptions.NotDataExistException()

        return@withContext recipeEntityList.map {
            Recipe(
                id = it.id,
                type = ModelType.RECIPE,
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