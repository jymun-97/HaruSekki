package com.jymun.harusekki.data.source.memo

import com.jymun.harusekki.data.db.memo.MemoDatabase
import com.jymun.harusekki.data.entity.memo.MemoEntity
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoLocalDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val db: MemoDatabase
) : MemoDataSource {

    override suspend fun loadAll(): List<MemoEntity> = withContext(dispatcherProvider.io) {

        return@withContext db.dao().loadAll()
    }

    override suspend fun insertMemo(memoEntity: MemoEntity) = withContext(dispatcherProvider.io) {
        db.dao().insertMemo(memoEntity)
    }

    override suspend fun deleteMemo(memoEntity: MemoEntity) = withContext(dispatcherProvider.io) {
        db.dao().deleteMemo(memoEntity)
    }
}