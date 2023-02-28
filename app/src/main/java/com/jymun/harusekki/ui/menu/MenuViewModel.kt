package com.jymun.harusekki.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jymun.harusekki.domain.menu.LoadDateUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadDateUseCase: LoadDateUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _dateSet = MutableLiveData<HashSet<CalendarDay>?>()
    val dateSet: LiveData<HashSet<CalendarDay>?>
        get() = _dateSet

    fun loadDate(year: Int, month: Int) = onMainDispatcher {
        _dateSet.postValue(loadDateUseCase(year, month))
    }

    fun addDate(date: CalendarDay) = onDefaultDispatcher {
        val set = dateSet.value ?: return@onDefaultDispatcher

        set.add(date)
        _dateSet.postValue(set)
    }

    fun deleteDate(date: CalendarDay) = onDefaultDispatcher {
        val set = dateSet.value ?: return@onDefaultDispatcher

        set.remove(date)
        _dateSet.postValue(set)
    }
}