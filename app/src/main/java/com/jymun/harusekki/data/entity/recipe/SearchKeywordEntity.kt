package com.jymun.harusekki.data.entity.recipe

import androidx.room.PrimaryKey
import com.jymun.harusekki.data.entity.Entity

@androidx.room.Entity(tableName = "search_keyword")
data class SearchKeywordEntity(
    override val id: Long = 0L,
    @PrimaryKey(autoGenerate = false)
    val keyword: String
) : Entity