package com.jymun.harusekki.ui.search_result

import android.os.Parcelable
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import kotlinx.parcelize.Parcelize

sealed class SearchMode(
    open var sortOption: RecipeSortOption
) : Parcelable {

    @Parcelize
    data class ByTitle(
        val keyword: String,
        override var sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class ByCategory(
        val category: String,
        override var sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class ByIngredient(
        override var sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class Favorite(
        override var sortOption: RecipeSortOption = RecipeSortOption.LATEST
    ) : SearchMode(sortOption)

    @Parcelize
    data class All(override var sortOption: RecipeSortOption) : SearchMode(sortOption)

}
