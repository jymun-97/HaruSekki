package com.jymun.harusekki.data.entity.menu

import androidx.room.PrimaryKey
import com.jymun.harusekki.data.entity.Entity

@androidx.room.Entity(
    tableName = "menu"
)
data class MenuEntity(
    override val id: Long = 0,
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val category: Int,
    val menuTitle: String,
    val recipeId: Long? = null,
    @PrimaryKey(autoGenerate = false)
    val pmKey: String = "$year-$month-$dayOfMonth-$category-$menuTitle"
) : Entity