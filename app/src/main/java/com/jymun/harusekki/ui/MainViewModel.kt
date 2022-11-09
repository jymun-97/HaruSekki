package com.jymun.harusekki.ui

import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    

}