package com.jymun.harusekki.ui.search_result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class SearchMode : Parcelable {

    @Parcelize
    data class ByTitle(val keyword: String) : SearchMode()

    @Parcelize
    data class ByCategory(val category: String) : SearchMode()

    @Parcelize
    object ByIngredient : SearchMode()

    @Parcelize
    object Best : SearchMode()

    @Parcelize
    object Favorite : SearchMode()

}
