package com.jymun.harusekki.ui.search_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val _searchMode = MutableLiveData<SearchMode>()
    val searchMode: LiveData<SearchMode>
        get() = _searchMode

    fun updateSearchMode(mode: SearchMode) = _searchMode.postValue(mode)
}