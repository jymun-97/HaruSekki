package com.jymun.harusekki.ui.home.recipe

import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.databinding.ItemRecipeLinearBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class RecipeLinearViewHolder(
    private val binding: ItemRecipeLinearBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<Recipe>(binding, resourcesProvider) {

    override fun bindData(model: Recipe, adapterListener: AdapterListener?) {
        binding.recipe = model
        binding.root.setOnClickListener {
            adapterListener?.let {
                (it as RecipeAdapterListener).onRecipeItemClicked(model)
            }
        }
    }
}