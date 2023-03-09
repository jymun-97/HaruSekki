package com.jymun.harusekki.data.source.memo

import com.jymun.harusekki.data.entity.memo.MemoEntity

interface MemoDataSource {

    suspend fun loadAll(): List<MemoEntity>

    suspend fun insertMemo(memoEntity: MemoEntity)

    suspend fun deleteMemo(memoEntity: MemoEntity)
}