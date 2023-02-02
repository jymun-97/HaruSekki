package com.jymun.harusekki.ui.search_result

import android.os.Parcelable
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import kotlinx.parcelize.Parcelize

sealed class SearchMode(
    open var sortOption: RecipeSortOption,
    open var category: RecipeCategory
) : Parcelable {

    @Parcelize
    data class ByTitle(
        val keyword: String,
        override var sortOption: RecipeSortOption = RecipeSortOption.LATEST,
        override var category: RecipeCategory = RecipeCategory.ALL
    ) : SearchMode(sortOption, category)

    @Parcelize
    data class ByIngredient(
        override var sortOption: RecipeSortOption = RecipeSortOption.LATEST,
        override var category: RecipeCategory = RecipeCategory.ALL
    ) : SearchMode(sortOption, category)

    @Parcelize
    data class Favorite(
        override var sortOption: RecipeSortOption = RecipeSortOption.LATEST,
        override var category: RecipeCategory = RecipeCategory.ALL
    ) : SearchMode(sortOption, category)

    @Parcelize
    data class All(
        override var sortOption: RecipeSortOption,
        override var category: RecipeCategory = RecipeCategory.ALL
    ) : SearchMode(sortOption, category)

}
