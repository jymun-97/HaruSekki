package com.jymun.harusekki.ui.search_recipe

import com.jymun.harusekki.data.model.recipe.SearchKeyword
import com.jymun.harusekki.databinding.ItemSearchKeywordBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class SearchKeywordViewHolder(
    private val binding: ItemSearchKeywordBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<SearchKeyword>(binding, resourcesProvider) {

    override fun bindData(model: SearchKeyword, adapterListener: AdapterListener?) {
        binding.apply {
            searchKeyword = model
            keywordTextView.setOnClickListener {
                adapterListener?.let {
                    (it as SearchKeywordAdapterListener).onKeywordTextClicked(model)
                }
            }
            deleteKeywordImageButton.setOnClickListener {
                adapterListener?.let {
                    (it as SearchKeywordAdapterListener).onDeleteButtonClicked(model)
                }
            }
        }
    }
}