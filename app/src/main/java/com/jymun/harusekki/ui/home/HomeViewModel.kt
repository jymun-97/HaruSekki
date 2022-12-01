package com.jymun.harusekki.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.domain.recipe.LoadAllRecipeUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByCategoryUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByIngredientUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByTitleUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.ui.home.recipe.RecipeSortBy
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadAllRecipeUseCase: LoadAllRecipeUseCase,
    private val searchRecipeByTitleUseCase: SearchRecipeByTitleUseCase,
    private val searchRecipeByCategoryUseCase: SearchRecipeByCategoryUseCase,
    private val searchRecipeByIngredientUseCase: SearchRecipeByIngredientUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _recipeList = MutableLiveData<List<Recipe>?>()
    val recipeList: LiveData<List<Recipe>?>
        get() = _recipeList

    private val _bestRecipeList = MutableLiveData<List<Recipe>?>()
    val bestRecipeList: LiveData<List<Recipe>?>
        get() = _bestRecipeList

    fun loadData() = onMainDispatcher {
        _recipeList.postValue(loadAllRecipeUseCase())
        _bestRecipeList.postValue(
            loadAllRecipeUseCase(
                orderBy = RecipeSortBy.LIKES_DESC,
                isGridType = true
            )
        )
    }
}