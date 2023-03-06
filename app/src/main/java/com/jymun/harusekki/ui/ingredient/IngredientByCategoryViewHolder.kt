package com.jymun.harusekki.ui.ingredient

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.ingredient.IngredientByCategory
import com.jymun.harusekki.databinding.ItemIngredientByCategoryBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider
import kotlin.math.min

class IngredientByCategoryViewHolder(
    private val binding: ItemIngredientByCategoryBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<IngredientByCategory>(binding, resourcesProvider) {

    override fun bindData(model: IngredientByCategory, adapterListener: AdapterListener?) {
        binding.ingredientCategoryTextView.text = model.category.text
        binding.ingredientCategoryImageView.setImageDrawable(
            resourcesProvider.getDrawable(
                model.category.iconResId
            )
        )

        binding.ingredientRecyclerView.apply {
            layoutManager = GridLayoutManager(
                context,
                min(3, model.ingredientList.size / 6 + 1),
                HORIZONTAL,
                false
            )
            adapter = ModelRecyclerAdapter<Ingredient>(resourcesProvider).apply {
                submitList(model.ingredientList)
                addAdapterListener(object : IngredientAdapterListener {
                    override fun onIngredientItemClicked(ingredient: Ingredient) {
                        if (!IngredientManager.isSelected(ingredient)) {
                            IngredientManager.addIngredient(ingredient)
                        } else {
                            IngredientManager.deleteIngredient(ingredient)
                        }
                    }
                })
            }
        }
    }
}