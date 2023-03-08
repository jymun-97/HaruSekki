package com.jymun.harusekki.data.db.memo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jymun.harusekki.data.entity.memo.MemoEntity

@Dao
interface MemoDao {

    @Query("SELECT * FROM memo")
    suspend fun loadAll(): List<MemoEntity>

    @Insert
    suspend fun insertMemo(memoEntity: MemoEntity)

    @Delete
    suspend fun deleteMemo(memoEntity: MemoEntity)
}