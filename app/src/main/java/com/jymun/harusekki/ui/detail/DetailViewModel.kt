package com.jymun.harusekki.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jymun.harusekki.data.model.recipe.RecipeDetail
import com.jymun.harusekki.domain.recipe.LoadRecipeDetailUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadRecipeDetailUseCase: LoadRecipeDetailUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _recipeDetail = MutableLiveData<RecipeDetail>()
    val recipeDetail: LiveData<RecipeDetail>
        get() = _recipeDetail

    fun loadData(recipeId: Long) = onMainDispatcher {
        _recipeDetail.postValue(loadRecipeDetailUseCase.invoke(recipeId))
    }
}