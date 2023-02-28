package com.jymun.harusekki.ui.menu

import com.jymun.harusekki.ui.base.adapter.AdapterListener

interface MenuAdapterListener : AdapterListener {

    fun onMenuItemClicked(recipeId: Long)
}