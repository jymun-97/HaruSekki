package com.jymun.harusekki.ui.search_result

import android.os.Parcelable
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import kotlinx.parcelize.Parcelize

sealed class SearchMode(
    open val sortOption: RecipeSortOption
) : Parcelable {

    @Parcelize
    data class ByTitle(
        val keyword: String,
        override val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class ByCategory(
        val category: String,
        override val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class ByIngredient(
        override val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class Favorite(
        override val sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class All(override val sortOption: RecipeSortOption) : SearchMode(sortOption)

}
