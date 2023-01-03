package com.jymun.harusekki.data.entity.recipe

import com.jymun.harusekki.data.entity.Entity

data class SearchKeywordEntity(
    override val id: Long,
    val keyword: String
) : Entity