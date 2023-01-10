package com.jymun.harusekki.domain.recipe

import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertLatestReadRecipeUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(recipe: Recipe) = withContext(dispatcherProvider.default) {
        recipeRepository.insertLatestReadRecipe(recipe.toEntity())
    }
}