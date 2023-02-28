package com.jymun.harusekki.domain.menu

import com.jymun.harusekki.data.repository.menu.MenuRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadDateUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val menuRepository: MenuRepository
) {

    suspend operator fun invoke(
        year: Int,
        month: Int
    ): HashSet<CalendarDay> = withContext(dispatcherProvider.default) {

        return@withContext menuRepository.loadMenu(year, month).map { menuEntity ->
            CalendarDay.from(
                menuEntity.year,
                menuEntity.month - 1,
                menuEntity.dayOfMonth
            )
        }.toHashSet()
    }
}