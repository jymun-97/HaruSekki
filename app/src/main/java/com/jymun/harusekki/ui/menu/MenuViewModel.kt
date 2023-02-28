package com.jymun.harusekki.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.jymun.harusekki.data.model.menu.Menu
import com.jymun.harusekki.data.model.menu.MenuCategory
import com.jymun.harusekki.domain.menu.DeleteMenuUseCase
import com.jymun.harusekki.domain.menu.InsertMenuUseCase
import com.jymun.harusekki.domain.menu.LoadDateUseCase
import com.jymun.harusekki.domain.menu.LoadMenuUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadMenuUseCase: LoadMenuUseCase,
    private val insertMenuUseCase: InsertMenuUseCase,
    private val deleteMenuUseCase: DeleteMenuUseCase,
    private val loadDateUseCase: LoadDateUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _menuList = MutableLiveData<List<Menu>?>()
    private val menuList: LiveData<List<Menu>?>
        get() = _menuList

    private val _dateSet = MutableLiveData<HashSet<CalendarDay>?>()
    val dateSet: LiveData<HashSet<CalendarDay>?>
        get() = _dateSet

    val breakfast = menuList.switchMap {
        val result = MutableLiveData<List<Menu>>()
        onMainDispatcher {
            result.postValue(withContext(dispatcherProvider.default) {
                it?.filter { menu -> menu.category == MenuCategory.BREAKFAST }
            })
        }
        result
    }

    val lunch = menuList.switchMap {
        val result = MutableLiveData<List<Menu>>()
        onMainDispatcher {
            result.postValue(withContext(dispatcherProvider.default) {
                it?.filter { menu -> menu.category == MenuCategory.LUNCH }
            })
        }
        result
    }

    val dinner = menuList.switchMap {
        val result = MutableLiveData<List<Menu>>()
        onMainDispatcher {
            result.postValue(withContext(dispatcherProvider.default) {
                it?.filter { menu -> menu.category == MenuCategory.DINNER }
            })
        }
        result
    }

    fun loadMenu(year: Int, month: Int, dayOfMonth: Int) = onMainDispatcher {
        _menuList.postValue(
            loadMenuUseCase(year, month, dayOfMonth)
        )
    }

    fun addMenu(
        menu: Menu,
        year: Int,
        month: Int,
        dayOfMonth: Int,
        onMenuAdded: () -> Unit
    ) = onMainDispatcher {
        insertMenuUseCase(menu)
        loadMenu(year, month, dayOfMonth)
        onMenuAdded()
    }

    fun deleteMenu(year: Int, month: Int, dayOfMonth: Int) = onMainDispatcher {
        deleteMenuUseCase(year, month, dayOfMonth)
        loadMenu(year, month, dayOfMonth)
    }

    fun pasteMenu(
        copiedYear: Int,
        copiedMonth: Int,
        copiedDayOfMonth: Int,
        pasteYear: Int,
        pasteMonth: Int,
        pasteDayOfMonth: Int
    ) = onMainDispatcher {

        val targets = loadMenuUseCase(copiedYear, copiedMonth, copiedDayOfMonth)
        insertMenuUseCase(pasteYear, pasteMonth, pasteDayOfMonth, targets)
        loadMenu(pasteYear, pasteMonth, pasteDayOfMonth)
    }

    fun loadDate(year: Int, month: Int) = onMainDispatcher {
        _dateSet.postValue(loadDateUseCase(year, month))
    }
}