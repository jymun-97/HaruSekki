package com.jymun.harusekki.domain.ingredient

import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.repository.ingredient.IngredientRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddIngredientInRefrigeratorUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val ingredientRepository: IngredientRepository
) {

    suspend operator fun invoke(
        ingredient: Ingredient
    ) = withContext(dispatcherProvider.default) {

        ingredientRepository.addIngredientInRefrigerator(
            ingredient.toEntity()
        )
    }
}