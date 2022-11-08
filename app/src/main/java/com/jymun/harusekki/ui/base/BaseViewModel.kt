package com.jymun.harusekki.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jymun.harusekki.di.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    dispatcherProvider: DispatcherProvider
) : ViewModel(), DispatcherProvider by dispatcherProvider {

    inline fun BaseViewModel.onMainDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch {
        body(this)
    }

    inline fun BaseViewModel.onIoDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(io) {
        body(this)
    }

    inline fun BaseViewModel.onDefaultDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(default) {
        body(this)
    }
}