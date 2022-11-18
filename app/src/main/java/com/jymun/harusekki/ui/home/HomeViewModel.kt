package com.jymun.harusekki.ui.home

import android.util.Log
import com.jymun.harusekki.data.service.SearchRecipeService
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val searchRecipeService: SearchRecipeService
) : BaseViewModel(dispatcherProvider) {

    fun loadAllRecipe() = onIoDispatcher {
        val response = searchRecipeService.loadAll("latest")
        if (response.isSuccessful) {
            response.body()?.let {
                it.forEach {
                    Log.d("# HomeViewModel", it.toString())
                }
            }
        }
    }
}