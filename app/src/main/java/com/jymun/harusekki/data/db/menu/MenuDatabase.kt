package com.jymun.harusekki.data.db.menu

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jymun.harusekki.data.entity.menu.MenuEntity

@Database(
    entities = [MenuEntity::class],
    version = 1
)
abstract class MenuDatabase : RoomDatabase() {

    abstract fun dao(): MenuDao
}