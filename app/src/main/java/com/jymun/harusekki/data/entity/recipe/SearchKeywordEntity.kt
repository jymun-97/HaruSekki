package com.jymun.harusekki.data.entity.recipe

import androidx.room.PrimaryKey
import com.jymun.harusekki.data.entity.Entity
import java.time.LocalDateTime

@androidx.room.Entity(tableName = "search_keyword")
data class SearchKeywordEntity(
    @PrimaryKey(autoGenerate = false)
    val keyword: String,
    val timestamp: String = LocalDateTime.now().toString()
) : Entity {

    override val id: Long
        get() = keyword.hashCode().toLong()
}