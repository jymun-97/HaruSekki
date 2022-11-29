package com.jymun.harusekki.ui.home.recipe.category

import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.recipe.RecipeCategory
import com.jymun.harusekki.util.resources.ResourcesProvider
import javax.inject.Inject

class RecipeCategoryProvider @Inject constructor(
    resourcesProvider: ResourcesProvider
) {
    private val recipeCategoryStringArrayRes =
        resourcesProvider.getStringArray(R.array.recipe_category_string_resources)
    private val recipeCategoryImageArrayRes =
        resourcesProvider.getDrawableIdArray(R.array.recipe_category_image_resources)

    private val recipeCategoryList: List<RecipeCategory> by lazy {
        val size = recipeCategoryImageArrayRes.length()
        (0 until size).map { index ->
            RecipeCategory(
                id = index.toLong(),
                type = ModelType.RECIPE_CATEGORY,
                name = recipeCategoryStringArrayRes[index],
                imgResId = recipeCategoryImageArrayRes.getResourceId(index, 0)
            )
        }
    }

    fun get() = recipeCategoryList
}