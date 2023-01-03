package com.jymun.harusekki.data.repository

import com.jymun.harusekki.data.entity.recipe.SearchKeywordEntity

interface SearchKeywordRepository {

    suspend fun addKeyword(keywordEntity: SearchKeywordEntity)

    suspend fun deleteKeyword(keywordEntity: SearchKeywordEntity)

    suspend fun loadAllKeywords(): List<SearchKeywordEntity>

    suspend fun deleteAllKeywords()
}