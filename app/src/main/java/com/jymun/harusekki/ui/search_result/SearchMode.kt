package com.jymun.harusekki.ui.search_result

import android.os.Parcelable
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import kotlinx.parcelize.Parcelize

sealed class SearchMode(
    sortOption: RecipeSortOption
) : Parcelable {

    @Parcelize
    data class ByTitle(
        val keyword: String,
        val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class ByCategory(
        val category: String,
        val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class ByIngredient(
        val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class Favorite(
        val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class All(val sortOption: RecipeSortOption) : SearchMode(sortOption)

}
