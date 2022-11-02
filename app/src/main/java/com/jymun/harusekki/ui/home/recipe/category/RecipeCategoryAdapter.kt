package com.jymun.harusekki.ui.home.recipe.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jymun.harusekki.databinding.ItemRecipeCategoryBinding
import com.jymun.harusekki.util.resources.ResourcesProvider

class RecipeCategoryAdapter(
    private val resourcesProvider: ResourcesProvider,
    private val onCategoryItemClicked: (RecipeCategory) -> Unit
) : ListAdapter<RecipeCategory, RecipeCategoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCategoryViewHolder {
        return RecipeCategoryViewHolder(
            ItemRecipeCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), resourcesProvider
        )
    }

    override fun onBindViewHolder(holder: RecipeCategoryViewHolder, position: Int) {
        holder.bindData(currentList[position], onCategoryItemClicked)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RecipeCategory>() {
            override fun areItemsTheSame(
                oldItem: RecipeCategory,
                newItem: RecipeCategory
            ): Boolean {
                return oldItem.textStrId == newItem.textStrId
            }

            override fun areContentsTheSame(
                oldItem: RecipeCategory,
                newItem: RecipeCategory
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}