package com.jymun.harusekki.ui.cooking_step

import com.jymun.harusekki.data.model.cooking_step.CookingStep
import com.jymun.harusekki.databinding.ItemCookingStepBinding
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.util.resources.ResourcesProvider

class CookingStepViewHolder(
    private val binding: ItemCookingStepBinding,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<CookingStep>(binding, resourcesProvider) {

    override fun bindData(model: CookingStep, adapterListener: AdapterListener?) {
        binding.cookingStep = model
    }
}