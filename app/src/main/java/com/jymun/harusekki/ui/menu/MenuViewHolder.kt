package com.jymun.harusekki.ui.menu

import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.menu.Menu
import com.jymun.harusekki.databinding.ItemMenuBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class MenuViewHolder(
    private val binding: ItemMenuBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<Menu>(binding, resourcesProvider) {

    override fun bindData(model: Menu, adapterListener: AdapterListener?) {
        binding.menu = model
        model.recipeId?.let {
            binding.menuTitleTextView.setTextColor(
                resourcesProvider.getColor(R.color.app_signature_dark)
            )
        }

        (adapterListener as? MenuAdapterListener)?.let { listener ->
            binding.root.setOnClickListener {
                model.recipeId?.let { listener.onMenuItemClicked(it) }
            }
        }
    }
}