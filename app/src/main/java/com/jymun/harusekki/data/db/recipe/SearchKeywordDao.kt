package com.jymun.harusekki.data.db.recipe

import androidx.room.*
import com.jymun.harusekki.data.entity.recipe.SearchKeywordEntity

@Dao
interface SearchKeywordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeyword(keyword: SearchKeywordEntity)

    @Delete
    suspend fun deleteKeyword(keyword: SearchKeywordEntity)

    @Query("SELECT * FROM search_keyword ORDER BY timestamp DESC")
    suspend fun loadAllKeywords(): List<SearchKeywordEntity>

    @Query("DELETE FROM search_keyword")
    suspend fun deleteAllKeywords()
}