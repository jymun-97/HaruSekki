package com.jymun.harusekki.domain.ingredient

import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.ingredient.IngredientCategoryMapper
import com.jymun.harusekki.data.repository.ingredient.IngredientRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchAllIngredientUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val ingredientRepository: IngredientRepository
) {

    suspend operator fun invoke() = withContext(dispatcherProvider.default) {

        return@withContext ingredientRepository.searchAll().map { ingredientEntity ->
            Ingredient(
                id = ingredientEntity.id,
                title = ingredientEntity.title,
                category = IngredientCategoryMapper.map(ingredientEntity.category),
                image = ingredientEntity.image
            )
        }
    }
}