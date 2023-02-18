package com.jymun.harusekki.data.source.menu

import com.jymun.harusekki.data.entity.menu.MenuEntity

interface MenuDataSource {

    suspend fun loadMenu(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<MenuEntity>

    suspend fun insertMenu(menuEntity: MenuEntity)

    suspend fun deleteMenu(menuEntity: MenuEntity)
}