package com.jymun.harusekki.data.model.cooking_step

import com.jymun.harusekki.data.entity.cooking_step.CookingStepEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class CookingStep(
    override val id: Long,
    override val type: ModelType = ModelType.COOKING_STEP,
    val description: String,
    val image: String
) : Model(id, type) {

    fun toEntity() = CookingStepEntity(
        id = id,
        description = description,
        image = image
    )
}