package com.jymun.harusekki.data.model.menu

import com.jymun.harusekki.data.entity.menu.MenuEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class Menu(
    override val id: Long = 0,
    override val type: ModelType = ModelType.MENU,
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val category: MenuCategory,
    val menuTitle: String,
    val recipeId: Long? = null
) : Model(id, type) {

    fun toEntity() = MenuEntity(
        id = id,
        year = year,
        month = month,
        dayOfMonth = dayOfMonth,
        category = category.id,
        menuTitle = menuTitle,
        recipeId = recipeId
    )
}