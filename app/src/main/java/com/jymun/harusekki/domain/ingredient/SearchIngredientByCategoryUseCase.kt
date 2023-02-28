package com.jymun.harusekki.domain.ingredient

import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.ingredient.IngredientByCategory
import com.jymun.harusekki.data.model.ingredient.IngredientCategory
import com.jymun.harusekki.data.model.ingredient.IngredientCategoryMapper
import com.jymun.harusekki.data.repository.ingredient.IngredientRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchIngredientByCategoryUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val ingredientRepository: IngredientRepository
) {

    suspend operator fun invoke(
        category: IngredientCategory
    ) = withContext(dispatcherProvider.default) {

        val ingredientList = ingredientRepository.searchByCategory(category.text).map {
            Ingredient(
                id = it.id,
                title = it.title,
                category = IngredientCategoryMapper.map(it.category),
                image = it.image
            )
        }
        return@withContext IngredientByCategory(
            id = category.ordinal.toLong(),
            type = ModelType.INGREDIENT_BY_CATEGORY,
            category = category,
            ingredientList = ingredientList
        )
    }
}