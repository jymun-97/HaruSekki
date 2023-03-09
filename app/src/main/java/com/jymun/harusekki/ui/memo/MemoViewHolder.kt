package com.jymun.harusekki.ui.memo

import com.jymun.harusekki.data.model.memo.Memo
import com.jymun.harusekki.databinding.ItemMemoBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class MemoViewHolder(
    private val binding: ItemMemoBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<Memo>(binding, resourcesProvider) {

    override fun bindData(model: Memo, adapterListener: AdapterListener?) {
        binding.memo = model
        (adapterListener as? MemoAdapterListener)?.let {
            binding.memoView.registerMemoChangedListener(it.onMemoChangedListener)
        }
    }
}