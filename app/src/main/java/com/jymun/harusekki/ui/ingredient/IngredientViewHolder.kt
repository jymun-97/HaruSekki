package com.jymun.harusekki.ui.ingredient

import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.databinding.ItemIngredientBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class IngredientViewHolder(
    private val binding: ItemIngredientBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<Ingredient>(binding, resourcesProvider) {

    override fun bindData(model: Ingredient, adapterListener: AdapterListener?) {
        binding.apply {
            ingredient = model
            root.layoutParams.width = getWidthAccordingToShowingCount()
            ingredientView.isSelected = IngredientManager.isSelected(model)

            (adapterListener as? IngredientAdapterListener)?.let { listener ->
                binding.ingredientView.apply {
                    checkEnabled = true
                    setOnClickListener {
                        listener.onIngredientItemClicked(model)
                        binding.ingredientView.isSelected = IngredientManager.isSelected(model)
                    }
                }
            } ?: run {
                binding.ingredientView.checkEnabled = false
            }
        }
    }

    private fun getWidthAccordingToShowingCount(): Int {
        val screenWidth = resourcesProvider.getScreenWidth()
        val padding = resourcesProvider.getDimension(R.dimen.horizontal_space)
        val width =
            (screenWidth - padding * 2) / resourcesProvider.getInteger(R.integer.count_showing_ingredient_items)

        return width.toInt()
    }
}