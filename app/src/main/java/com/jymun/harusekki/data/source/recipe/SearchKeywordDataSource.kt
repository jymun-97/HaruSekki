package com.jymun.harusekki.data.source.recipe

import com.jymun.harusekki.data.entity.recipe.SearchKeywordEntity

interface SearchKeywordDataSource {

    suspend fun insertKeyword(keywordEntity: SearchKeywordEntity)

    suspend fun deleteKeyword(keywordEntity: SearchKeywordEntity)

    suspend fun loadAllKeywords(): List<SearchKeywordEntity>

    suspend fun deleteAllKeywords()
}