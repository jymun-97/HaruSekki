package com.jymun.harusekki.ui.custom_view

import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.memo.Memo

interface OnMemoChangedListener {

    fun onMemoCheckedChanged(memo: Memo)

    fun onMemoUpdated(memo: Memo)

    fun onMemoDeleted(memo: Memo)

    fun onMemoTextChanged(
        newText: String,
        onSearchResult: (List<Ingredient>) -> Unit
    )
}