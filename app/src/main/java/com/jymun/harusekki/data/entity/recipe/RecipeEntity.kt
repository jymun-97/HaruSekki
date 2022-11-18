package com.jymun.harusekki.data.entity.recipe

import com.jymun.harusekki.data.entity.Entity

data class RecipeEntity(
    override val id: Long,
    val title: String,
    val category: String,
    val summary: String,
    val imgList: List<String>,
    val hits: Long,
    val likes: Long,
) : Entity
