package com.jymun.harusekki.domain.recipe

import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteOldestReadRecipeUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke() = withContext(dispatcherProvider.default) {
        recipeRepository.deleteOldestReadRecipe()
    }
}