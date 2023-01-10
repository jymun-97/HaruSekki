package com.jymun.harusekki.domain.recipe

import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryProvider
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.jymun.harusekki.util.exception.CustomExceptions
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadLatestReadRecipeUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(): List<Recipe> = withContext(dispatcherProvider.default) {

        val latestReadRecipeList = recipeRepository.loadLatestReadRecipe()
        if (latestReadRecipeList.isEmpty()) throw CustomExceptions.NotDataExistException()

        return@withContext latestReadRecipeList.map {
            Recipe(
                id = it.id,
                type = ModelType.RECIPE_GRID,
                title = it.title,
                category = RecipeCategoryProvider.get(it.category),
                summary = it.summary,
                hits = it.hits,
                likes = it.likes,
                imgList = it.imgList
            )
        }
    }
}