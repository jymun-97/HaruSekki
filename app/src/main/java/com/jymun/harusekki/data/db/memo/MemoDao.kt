package com.jymun.harusekki.data.db.memo

import androidx.room.*
import com.jymun.harusekki.data.entity.memo.MemoEntity

@Dao
interface MemoDao {

    @Query("SELECT * FROM memo")
    suspend fun loadAll(): List<MemoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemo(memoEntity: MemoEntity)

    @Delete
    suspend fun deleteMemo(memoEntity: MemoEntity)
}