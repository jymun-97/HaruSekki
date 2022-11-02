package com.jymun.harusekki.ui.home.recipe.category

import androidx.recyclerview.widget.RecyclerView
import com.jymun.harusekki.databinding.ItemRecipeCategoryBinding
import com.jymun.harusekki.util.resources.ResourcesProvider

class RecipeCategoryViewHolder(
    private val binding: ItemRecipeCategoryBinding,
    private val resourcesProvider: ResourcesProvider
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(model: RecipeCategory, onCategoryItemClicked: (RecipeCategory) -> Unit) {
        binding.categoryTextView.text =
            resourcesProvider.getString(model.textStrId).replaceFirst(" / ", "\n")
        binding.categoryImageView.setImageDrawable(
            resourcesProvider.getDrawable(model.imageResId)
        )
        binding.root.setOnClickListener { _ ->
            onCategoryItemClicked(model)
        }
    }
}