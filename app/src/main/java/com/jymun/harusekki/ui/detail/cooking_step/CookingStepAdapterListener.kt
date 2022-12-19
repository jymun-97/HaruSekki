package com.jymun.harusekki.ui.detail.cooking_step

import com.jymun.harusekki.ui.base.adapter.AdapterListener

interface CookingStepAdapterListener : AdapterListener {

    fun onCookingStepImageClicked(imageUrl: String)
}