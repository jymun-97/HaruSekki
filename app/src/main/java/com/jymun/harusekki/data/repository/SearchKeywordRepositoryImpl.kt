package com.jymun.harusekki.data.repository

import com.jymun.harusekki.data.entity.recipe.SearchKeywordEntity
import com.jymun.harusekki.data.source.recipe.SearchKeywordDataSource
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchKeywordRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val searchKeywordLocalDataSource: SearchKeywordDataSource
) : SearchKeywordRepository {

    override suspend fun addKeyword(
        keywordEntity: SearchKeywordEntity
    ) = withContext(dispatcherProvider.io) {

        return@withContext searchKeywordLocalDataSource.insertKeyword(keywordEntity)
    }

    override suspend fun deleteKeyword(
        keywordEntity: SearchKeywordEntity
    ) = withContext(dispatcherProvider.io) {

        return@withContext searchKeywordLocalDataSource.deleteKeyword(keywordEntity)
    }

    override suspend fun loadAllKeywords(): List<SearchKeywordEntity> =
        withContext(dispatcherProvider.io) {

            return@withContext searchKeywordLocalDataSource.loadAllKeywords()
        }

    override suspend fun deleteAllKeywords() = withContext(dispatcherProvider.io) {

        return@withContext searchKeywordLocalDataSource.deleteAllKeywords()
    }
}