package com.jymun.harusekki.data.source.recipe

import com.jymun.harusekki.data.db.recipe.SearchKeywordDatabase
import com.jymun.harusekki.data.entity.recipe.SearchKeywordEntity
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchKeywordLocalDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val db: SearchKeywordDatabase
) : SearchKeywordDataSource {

    override suspend fun insertKeyword(
        keywordEntity: SearchKeywordEntity
    ) = withContext(dispatcherProvider.io) {

        return@withContext db.dao().insertKeyword(keywordEntity)
    }

    override suspend fun deleteKeyword(
        keywordEntity: SearchKeywordEntity
    ) = withContext(dispatcherProvider.io) {

        return@withContext db.dao().deleteKeyword(keywordEntity)
    }

    override suspend fun loadAllKeywords(): List<SearchKeywordEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext db.dao().loadAllKeywords()
        }

    override suspend fun deleteAllKeywords() = withContext(dispatcherProvider.io) {

        return@withContext db.dao().deleteAllKeywords()
    }
}