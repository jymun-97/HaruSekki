package com.jymun.harusekki.data.model.recipe

import com.jymun.harusekki.data.entity.recipe.SearchKeywordEntity
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType

data class SearchKeyword(
    override val id: Long = 0L,
    override val type: ModelType = ModelType.SEARCH_KEYWORD,
    val keyword: String
) : Model(id, type) {

    fun toEntity() = SearchKeywordEntity(
        id = id,
        keyword = keyword
    )
}