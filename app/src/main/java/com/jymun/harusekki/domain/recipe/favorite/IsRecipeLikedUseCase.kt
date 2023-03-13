package com.jymun.harusekki.domain.recipe.favorite

import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsRecipeLikedUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        id: Long
    ) = withContext(dispatcherProvider.default) {

        return@withContext recipeRepository.isRecipeLiked(id)
    }
}