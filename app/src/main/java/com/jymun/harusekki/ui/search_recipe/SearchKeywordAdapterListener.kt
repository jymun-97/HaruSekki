package com.jymun.harusekki.ui.search_recipe

import com.jymun.harusekki.data.model.recipe.SearchKeyword
import com.jymun.harusekki.ui.base.adapter.AdapterListener

interface SearchKeywordAdapterListener : AdapterListener {

    fun onKeywordTextClicked(searchKeyword: SearchKeyword)

    fun onDeleteButtonClicked(searchKeyword: SearchKeyword)
}