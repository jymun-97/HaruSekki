package com.jymun.harusekki.data.entity.cooking_step

import com.jymun.harusekki.data.entity.Entity

data class CookingStepEntity(
    override val id: Long,
    val description: String,
    val image: String
) : Entity