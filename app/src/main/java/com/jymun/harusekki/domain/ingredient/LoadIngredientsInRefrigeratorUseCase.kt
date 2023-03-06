package com.jymun.harusekki.domain.ingredient

import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.ingredient.IngredientCategoryMapper
import com.jymun.harusekki.data.repository.ingredient.IngredientRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadIngredientsInRefrigeratorUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val ingredientRepository: IngredientRepository
) {

    suspend operator fun invoke() = withContext(dispatcherProvider.default) {

        return@withContext ingredientRepository.loadIngredientsInRefrigerator().map {
            Ingredient(
                id = it.id,
                title = it.title,
                category = IngredientCategoryMapper.map(it.category),
                image = it.image
            )
        }
    }
}