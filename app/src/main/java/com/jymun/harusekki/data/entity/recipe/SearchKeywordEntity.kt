package com.jymun.harusekki.data.entity.recipe

import androidx.room.PrimaryKey
import com.jymun.harusekki.data.entity.Entity

@androidx.room.Entity(tableName = "search_keyword")
data class SearchKeywordEntity(
    @PrimaryKey(autoGenerate = false)
    val keyword: String
) : Entity {

    override val id: Long
        get() = keyword.hashCode().toLong()
}