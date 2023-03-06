package com.jymun.harusekki.ui.ingredient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.jymun.harusekki.data.model.ingredient.IngredientByCategory
import com.jymun.harusekki.data.model.ingredient.IngredientCategory
import com.jymun.harusekki.domain.ingredient.AddIngredientInRefrigeratorUseCase
import com.jymun.harusekki.domain.ingredient.SearchIngredientByCategoryUseCase
import com.jymun.harusekki.domain.ingredient.SearchIngredientByTitleUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val searchIngredientByTitleUseCase: SearchIngredientByTitleUseCase,
    private val searchIngredientByCategoryUseCase: SearchIngredientByCategoryUseCase,
    private val addIngredientInRefrigeratorUseCase: AddIngredientInRefrigeratorUseCase,
) : BaseViewModel(dispatcherProvider) {

    val searchKeyword = MutableLiveData<String>()

    val ingredientByCategoryList = searchKeyword.switchMap { keyword ->
        val result = MutableLiveData<List<IngredientByCategory>>()
        onMainDispatcher {
            result.postValue(
                IngredientCategory.values().map { category ->
                    if (keyword.isNullOrEmpty()) {
                        searchIngredientByCategoryUseCase(category)
                    } else {
                        searchIngredientByTitleUseCase(keyword, category)
                    }
                }
            )
        }
        result
    }

    fun addSelectedIngredients(
        onFinished: () -> Unit
    ) = onMainDispatcher {
        IngredientManager.getSelectedIngredients().forEach {
            addIngredientInRefrigeratorUseCase(it)
        }
        onFinished()
    }
}