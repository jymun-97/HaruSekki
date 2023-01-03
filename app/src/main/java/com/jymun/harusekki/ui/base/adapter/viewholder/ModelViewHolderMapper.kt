package com.jymun.harusekki.ui.base.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.databinding.*
import com.jymun.harusekki.ui.detail.cooking_step.CookingStepViewHolder
import com.jymun.harusekki.ui.home.recipe.RecipeGridViewHolder
import com.jymun.harusekki.ui.home.recipe.RecipeLinearViewHolder
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryViewHolder
import com.jymun.harusekki.ui.ingredient.IngredientViewHolder
import com.jymun.harusekki.ui.search_recipe.SearchKeywordViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: ModelType,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        return when (type) {
            ModelType.RECIPE_CATEGORY -> RecipeCategoryViewHolder(
                ItemRecipeCategoryBinding.inflate(inflater, parent, false),
                resourcesProvider
            )
            ModelType.RECIPE_GRID -> RecipeGridViewHolder(
                ItemRecipeGridBinding.inflate(inflater, parent, false),
                resourcesProvider
            )
            ModelType.RECIPE_LINEAR -> RecipeLinearViewHolder(
                ItemRecipeLinearBinding.inflate(inflater, parent, false),
                resourcesProvider
            )
            ModelType.INGREDIENT -> IngredientViewHolder(
                ItemIngredientBinding.inflate(inflater, parent, false),
                resourcesProvider
            )
            ModelType.COOKING_STEP -> CookingStepViewHolder(
                ItemCookingStepBinding.inflate(inflater, parent, false),
                resourcesProvider
            )
            ModelType.SEARCH_KEYWORD -> SearchKeywordViewHolder(
                ItemSearchKeywordBinding.inflate(inflater, parent, false),
                resourcesProvider
            )
            else -> RecipeCategoryViewHolder(
                ItemRecipeCategoryBinding.inflate(inflater, parent, false),
                resourcesProvider
            )
        } as ModelViewHolder<M>
    }
}