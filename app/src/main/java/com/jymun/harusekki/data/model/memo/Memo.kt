package com.jymun.harusekki.data.model.memo

import com.jymun.harusekki.data.entity.memo.MemoEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.ingredient.Ingredient

data class Memo(
    override val id: Long = 0L,
    override val type: ModelType = ModelType.MEMO,
    val text: String = "",
    val ingredient: Ingredient? = null,
    val isChecked: Boolean = false
) : Model(id, type) {

    fun toEntity() = MemoEntity(
        id = id,
        text = text,
        ingredient = ingredient?.toEntity(),
        isChecked = isChecked
    )

    fun isEmptyMemo() = text.isEmpty()

    companion object {
        fun getEmptyMemo() = Memo()
    }
}