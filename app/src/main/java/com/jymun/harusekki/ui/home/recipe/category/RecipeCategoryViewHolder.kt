package com.jymun.harusekki.ui.home.recipe.category

import com.jymun.harusekki.data.model.recipe.RecipeCategory
import com.jymun.harusekki.databinding.ItemRecipeCategoryBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class RecipeCategoryViewHolder(
    private val binding: ItemRecipeCategoryBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<RecipeCategory>(binding, resourcesProvider) {

    override fun bindData(model: RecipeCategory, adapterListener: AdapterListener?) {
        binding.recipeCategory = model
        binding.categoryImageView.setImageDrawable(
            resourcesProvider.getDrawable(model.imgResId)
        )
    }
}