package com.jymun.harusekki.data.db.menu

import androidx.room.*
import com.jymun.harusekki.data.entity.menu.MenuEntity

@Dao
interface MenuDao {

    @Query("SELECT * FROM menu WHERE year = :year AND month = :month AND dayOfMonth = :dayOfMonth")
    suspend fun loadMenu(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<MenuEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(menuEntity: MenuEntity)

    @Delete
    suspend fun deleteMenu(menuEntity: MenuEntity)
}