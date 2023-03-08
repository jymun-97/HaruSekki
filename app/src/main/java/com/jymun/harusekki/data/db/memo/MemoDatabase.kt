package com.jymun.harusekki.data.db.memo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jymun.harusekki.data.entity.memo.MemoEntity

@Database(
    entities = [MemoEntity::class],
    version = 1
)
abstract class MemoDatabase : RoomDatabase() {

    abstract fun dao(): MemoDao
}