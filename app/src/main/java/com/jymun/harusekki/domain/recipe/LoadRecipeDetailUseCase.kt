package com.jymun.harusekki.domain.recipe

import com.jymun.harusekki.data.entity.cooking_step.CookingStepEntity
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.cooking_step.CookingStep
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.ingredient.IngredientCategoryMapper
import com.jymun.harusekki.data.model.recipe.RecipeDetail
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryMapper
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadRecipeDetailUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        recipeId: Long,
        refreshFlag: Boolean = false
    ): RecipeDetail = withContext(dispatcherProvider.default) {

        val recipeEntity = recipeRepository.loadDetail(recipeId, refreshFlag)
        return@withContext RecipeDetail(
            id = recipeEntity.id,
            type = ModelType.RECIPE_LINEAR,
            title = recipeEntity.title,
            category = RecipeCategoryMapper.map(recipeEntity.category),
            summary = recipeEntity.summary,
            imgList = recipeEntity.imgList,
            hits = recipeEntity.hits,
            likes = recipeEntity.likes,
            ingredientList = recipeEntity.ingredientList!!.map { convertToIngredientModel(it) },
            cookingStepList = recipeEntity.cookingStepList!!.map { convertToCookingStepModel(it) }
        )
    }

    private fun convertToIngredientModel(ingredientEntity: IngredientEntity) = Ingredient(
        id = ingredientEntity.id,
        type = ModelType.INGREDIENT,
        title = ingredientEntity.title,
        category = IngredientCategoryMapper.map(ingredientEntity.category),
        image = ingredientEntity.image
    )

    private fun convertToCookingStepModel(cookingStepEntity: CookingStepEntity) = CookingStep(
        id = cookingStepEntity.id,
        type = ModelType.COOKING_STEP,
        description = cookingStepEntity.description,
        image = cookingStepEntity.image
    )
}