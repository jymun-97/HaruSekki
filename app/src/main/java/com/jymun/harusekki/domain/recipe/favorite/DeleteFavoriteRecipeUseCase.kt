package com.jymun.harusekki.domain.recipe.favorite

import com.jymun.harusekki.data.model.recipe.RecipeDetail
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteFavoriteRecipeUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        recipe: RecipeDetail
    ) = withContext(dispatcherProvider.default) {

        recipeRepository.deleteFavoriteRecipe(recipe.toEntity())
    }
}