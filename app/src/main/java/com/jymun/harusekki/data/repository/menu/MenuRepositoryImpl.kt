package com.jymun.harusekki.data.repository.menu

import com.jymun.harusekki.data.entity.menu.MenuEntity
import com.jymun.harusekki.data.source.menu.MenuLocalDataSource
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val menuLocalDataSource: MenuLocalDataSource
) : MenuRepository {

    override suspend fun loadMenu(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<MenuEntity> = withContext(dispatcherProvider.io) {

        return@withContext menuLocalDataSource.loadMenu(year, month, dayOfMonth)
    }

    override suspend fun insertMenu(menuEntity: MenuEntity) = withContext(dispatcherProvider.io) {

        return@withContext menuLocalDataSource.insertMenu(menuEntity)
    }

    override suspend fun deleteMenu(menuEntity: MenuEntity) = withContext(dispatcherProvider.io) {

        return@withContext menuLocalDataSource.deleteMenu(menuEntity)
    }

}