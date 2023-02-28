package com.jymun.harusekki.ui.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.databinding.ItemIngredientByCategoryBinding
import com.jymun.harusekki.util.resources.ResourcesProvider

class IngredientByCategoryAdapter(
    private val resourcesProvider: ResourcesProvider
) : ListAdapter<List<Ingredient>, IngredientByCategoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientByCategoryViewHolder {
        return IngredientByCategoryViewHolder(
            ItemIngredientByCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            resourcesProvider,
        )
    }

    override fun onBindViewHolder(holder: IngredientByCategoryViewHolder, position: Int) {
        
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<List<Ingredient>>() {
            override fun areItemsTheSame(
                oldItem: List<Ingredient>,
                newItem: List<Ingredient>
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: List<Ingredient>,
                newItem: List<Ingredient>
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}