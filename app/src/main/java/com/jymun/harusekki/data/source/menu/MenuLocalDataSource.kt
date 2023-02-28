package com.jymun.harusekki.data.source.menu

import com.jymun.harusekki.data.db.menu.MenuDatabase
import com.jymun.harusekki.data.entity.menu.MenuEntity
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuLocalDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val db: MenuDatabase
) : MenuDataSource {

    override suspend fun loadMenu(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<MenuEntity> = withContext(dispatcherProvider.io) {

        return@withContext db.dao().loadMenu(year, month, dayOfMonth)
    }

    override suspend fun loadMenu(
        year: Int,
        month: Int,
    ): List<MenuEntity> = withContext(dispatcherProvider.io) {

        return@withContext db.dao().loadMenu(year, month)
    }

    override suspend fun insertMenu(menuEntity: MenuEntity) = withContext(dispatcherProvider.io) {

        return@withContext db.dao().insertMenu(menuEntity)
    }

    override suspend fun deleteMenu(menuEntity: MenuEntity) = withContext(dispatcherProvider.io) {

        return@withContext db.dao().deleteMenu(menuEntity)
    }

    override suspend fun deleteMenu(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) = withContext(dispatcherProvider.io) {

        return@withContext db.dao().deleteMenu(year, month, dayOfMonth)
    }
}