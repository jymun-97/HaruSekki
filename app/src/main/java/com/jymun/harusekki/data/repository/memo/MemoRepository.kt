package com.jymun.harusekki.data.repository.memo

import com.jymun.harusekki.data.entity.memo.MemoEntity

interface MemoRepository {

    suspend fun loadAll(): List<MemoEntity>

    suspend fun insertMemo(memoEntity: MemoEntity)

    suspend fun deleteMemo(memoEntity: MemoEntity)
}