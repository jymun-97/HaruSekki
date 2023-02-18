package com.jymun.harusekki.ui.menu

import com.jymun.harusekki.data.model.menu.Menu
import com.jymun.harusekki.ui.base.adapter.AdapterListener

interface MenuAdapterListener : AdapterListener {

    fun onDeleteMenuButtonClicked(menu: Menu)

    fun onMenuItemClicked(recipeId: Long)
}