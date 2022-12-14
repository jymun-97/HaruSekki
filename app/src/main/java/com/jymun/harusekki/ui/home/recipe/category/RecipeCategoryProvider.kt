package com.jymun.harusekki.ui.home.recipe.category

import android.content.res.TypedArray
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.recipe.RecipeCategory
import com.jymun.harusekki.util.resources.ResourcesProvider

object RecipeCategoryProvider {
    private lateinit var recipeCategoryStringArrayRes: Array<String>
    private lateinit var recipeCategoryImageArrayRes: TypedArray
    private var recipeCategoryList: List<RecipeCategory>? = null

    private fun generate(resourcesProvider: ResourcesProvider) {
        recipeCategoryStringArrayRes =
            resourcesProvider.getStringArray(R.array.recipe_category_string_resources)
        recipeCategoryImageArrayRes =
            resourcesProvider.getDrawableIdArray(R.array.recipe_category_image_resources)

        val size = recipeCategoryImageArrayRes.length()
        recipeCategoryList = (0 until size).map { index ->
            RecipeCategory(
                id = index.toLong(),
                type = ModelType.RECIPE_CATEGORY,
                name = recipeCategoryStringArrayRes[index],
                imgResId = recipeCategoryImageArrayRes.getResourceId(index, 0)
            )
        }
    }

    fun get(resourcesProvider: ResourcesProvider): List<RecipeCategory> {
        recipeCategoryList ?: generate(resourcesProvider)
        return recipeCategoryList!!
    }

    fun get(categoryStr: String) =
        recipeCategoryList!![recipeCategoryStringArrayRes.indexOf(categoryStr)]
}