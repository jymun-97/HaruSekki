package com.jymun.harusekki.domain.menu

import com.jymun.harusekki.data.model.menu.Menu
import com.jymun.harusekki.data.repository.menu.MenuRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertMenuUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val menuRepository: MenuRepository
) {

    suspend operator fun invoke(menu: Menu) = withContext(dispatcherProvider.default) {

        menuRepository.insertMenu(menu.toEntity())
    }

    suspend operator fun invoke(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        menuList: List<Menu>
    ) = withContext(dispatcherProvider.default) {

        menuList.map {
            Menu(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                category = it.category,
                menuTitle = it.menuTitle,
                recipeId = it.recipeId
            ).toEntity()
        }.forEach {
            withContext(dispatcherProvider.io) {
                menuRepository.insertMenu(it)
            }
        }
    }
}