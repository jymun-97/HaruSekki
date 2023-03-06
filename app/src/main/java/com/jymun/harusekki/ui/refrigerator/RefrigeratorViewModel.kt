package com.jymun.harusekki.ui.refrigerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jymun.harusekki.data.model.ingredient.IngredientByCategory
import com.jymun.harusekki.data.model.ingredient.IngredientCategory
import com.jymun.harusekki.domain.ingredient.LoadIngredientsInRefrigeratorUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RefrigeratorViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadIngredientsInRefrigeratorUseCase: LoadIngredientsInRefrigeratorUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _ingredientByCategoryList = MutableLiveData<List<IngredientByCategory>>()
    val ingredientByCategoryList: LiveData<List<IngredientByCategory>>
        get() = _ingredientByCategoryList

    fun loadIngredients() = onMainDispatcher {
        _ingredientByCategoryList.postValue(
            IngredientCategory.values().map { category ->
                loadIngredientsInRefrigeratorUseCase(category)
            }
        )
    }
}