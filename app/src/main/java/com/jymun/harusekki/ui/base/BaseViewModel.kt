package com.jymun.harusekki.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.jymun.harusekki.util.exception.CustomExceptions
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel(
    dispatcherProvider: DispatcherProvider
) : ViewModel(), DispatcherProvider by dispatcherProvider {

    private val _loadState = MutableLiveData<LoadState>()
    val loadState: LiveData<LoadState>
        get() = _loadState

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException, is ConnectException -> _loadState.postValue(
                LoadState.Error(CustomExceptions.InvalidNetworkException())
            )
            is SocketException, is SocketTimeoutException -> _loadState.postValue(
                LoadState.Error(CustomExceptions.FailToConnectServerException())
            )
            else -> _loadState.postValue(
                LoadState.Error(CustomExceptions.InvalidAccessException())
            )
        }
    }

    protected inline fun BaseViewModel.onMainDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(main + exceptionHandler) {
        body(this)
    }

    protected inline fun BaseViewModel.onIoDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(io + exceptionHandler) {
        body(this)
    }

    protected inline fun BaseViewModel.onDefaultDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(default + exceptionHandler) {
        body(this)
    }
}