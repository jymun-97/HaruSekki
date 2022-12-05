package com.jymun.harusekki.ui.home.recipe

import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.databinding.ItemRecipeGridBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class RecipeGridViewHolder(
    private val binding: ItemRecipeGridBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<Recipe>(binding, resourcesProvider) {

    override fun bindData(model: Recipe, adapterListener: AdapterListener?) {
        binding.apply {
            recipe = model
            root.layoutParams.width = getWidthAccordingToShowingCount()
            root.setOnClickListener {
                adapterListener?.let {
                    (it as RecipeAdapterListener).onRecipeItemClicked(model)
                }
            }
        }
    }

    private fun getWidthAccordingToShowingCount(): Int {
        val screenWidth = resourcesProvider.getScreenWidth()
        val padding = resourcesProvider.getDimension(R.dimen.horizontal_space)
        val width = (screenWidth - padding) / SHOWING_ITEM_COUNT

        return width.toInt()
    }

    companion object {
        private const val SHOWING_ITEM_COUNT = 2.5
    }
}