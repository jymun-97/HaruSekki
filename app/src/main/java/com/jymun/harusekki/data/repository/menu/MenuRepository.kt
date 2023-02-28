package com.jymun.harusekki.data.repository.menu

import com.jymun.harusekki.data.entity.menu.MenuEntity

interface MenuRepository {

    suspend fun loadMenu(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<MenuEntity>

    suspend fun loadMenu(
        year: Int,
        month: Int,
    ): List<MenuEntity>

    suspend fun insertMenu(menuEntity: MenuEntity)

    suspend fun deleteMenu(menuEntity: MenuEntity)

    suspend fun deleteMenu(year: Int, month: Int, dayOfMonth: Int)
}