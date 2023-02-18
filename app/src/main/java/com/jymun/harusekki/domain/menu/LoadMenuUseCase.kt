package com.jymun.harusekki.domain.menu

import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.menu.Menu
import com.jymun.harusekki.data.model.menu.MenuCategory
import com.jymun.harusekki.data.repository.menu.MenuRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadMenuUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val menuRepository: MenuRepository
) {

    suspend operator fun invoke(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<Menu> = withContext(dispatcherProvider.default) {

        return@withContext menuRepository.loadMenu(year, month, dayOfMonth).map { menuEntity ->
            Menu(
                id = menuEntity.id,
                type = ModelType.MENU,
                year = menuEntity.year,
                month = menuEntity.month,
                dayOfMonth = menuEntity.dayOfMonth,
                category = MenuCategory.values()[menuEntity.category],
                menuTitle = menuEntity.menuTitle,
                recipeId = menuEntity.recipeId
            )
        }
    }
}