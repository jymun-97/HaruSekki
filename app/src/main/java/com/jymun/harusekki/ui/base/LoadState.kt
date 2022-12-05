package com.jymun.harusekki.ui.base

import com.jymun.harusekki.util.exception.CustomExceptions

sealed class LoadState {

    object Success : LoadState()

    data class Error(val exception: CustomExceptions) : LoadState()

    object Loading : LoadState()

}