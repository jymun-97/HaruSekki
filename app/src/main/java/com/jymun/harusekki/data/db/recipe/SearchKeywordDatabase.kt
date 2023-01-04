package com.jymun.harusekki.data.db.recipe

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jymun.harusekki.data.entity.recipe.SearchKeywordEntity

@Database(
    entities = [SearchKeywordEntity::class],
    version = 1
)
abstract class SearchKeywordDatabase : RoomDatabase() {

    abstract fun dao(): SearchKeywordDao
}