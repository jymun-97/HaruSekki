package com.jymun.harusekki.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

abstract class BaseViewModel(
    dispatcherProvider: DispatcherProvider
) : ViewModel(), DispatcherProvider by dispatcherProvider {

    protected val _loadState = MutableLiveData<String>()
    val loadState: LiveData<String>
        get() = _loadState

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        when (throwable) {
            is SocketException -> _loadState.postValue("인터넷 연결")

            is HttpException, is NullPointerException -> _loadState.postValue("파싱 에러")

            is UnknownHostException -> _loadState.postValue("잘못된 연결")

            else -> _loadState.postValue("실패")
        }
    }

    protected inline fun BaseViewModel.onMainDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch {
        body(this)
    }

    protected inline fun BaseViewModel.onIoDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(io + exceptionHandler) {
        body(this)
    }

    protected inline fun BaseViewModel.onDefaultDispatcher(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(default) {
        body(this)
    }
}