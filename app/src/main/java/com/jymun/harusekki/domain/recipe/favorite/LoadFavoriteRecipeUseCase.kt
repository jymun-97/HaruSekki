package com.jymun.harusekki.domain.recipe.favorite

import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryMapper
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadFavoriteRecipeUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        orderBy: RecipeSortOption = RecipeSortOption.LATEST,
        category: RecipeCategory,
        isGridType: Boolean = false
    ): List<Recipe> = withContext(dispatcherProvider.default) {

        return@withContext when (orderBy) {

            RecipeSortOption.LATEST -> recipeRepository.loadLatestFavoriteRecipe()

            RecipeSortOption.HITS -> recipeRepository.loadMostHitsFavoriteRecipe()

            RecipeSortOption.LIKES -> recipeRepository.loadMostLikesFavoriteRecipe()

        }.map {
            Recipe(
                id = it.id,
                type = if (isGridType) ModelType.RECIPE_GRID else ModelType.RECIPE_LINEAR,
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