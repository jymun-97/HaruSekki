package com.jymun.harusekki.data.repository.memo

import com.jymun.harusekki.data.entity.memo.MemoEntity
import com.jymun.harusekki.data.source.memo.MemoLocalDataSource
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val memoLocalDataSource: MemoLocalDataSource
) : MemoRepository {

    override suspend fun loadAll(): List<MemoEntity> = withContext(dispatcherProvider.io) {

        return@withContext memoLocalDataSource.loadAll()
    }

    override suspend fun insertMemo(memoEntity: MemoEntity) = withContext(dispatcherProvider.io) {
        memoLocalDataSource.insertMemo(memoEntity)
    }

    override suspend fun deleteMemo(memoEntity: MemoEntity) = withContext(dispatcherProvider.io) {
        memoLocalDataSource.deleteMemo(memoEntity)
    }
}