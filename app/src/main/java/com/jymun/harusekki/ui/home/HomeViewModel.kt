package com.jymun.harusekki.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.domain.recipe.DeleteOldestReadRecipeUseCase
import com.jymun.harusekki.domain.recipe.InsertLatestReadRecipeUseCase
import com.jymun.harusekki.domain.recipe.LoadAllRecipeUseCase
import com.jymun.harusekki.domain.recipe.favorite.LoadFavoriteRecipeUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadAllRecipeUseCase: LoadAllRecipeUseCase,
    private val insertLatestReadRecipeUseCase: InsertLatestReadRecipeUseCase,
    private val deleteOldestReadRecipeUseCase: DeleteOldestReadRecipeUseCase,
    private val loadFavoriteRecipeUseCase: LoadFavoriteRecipeUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _recipeList = MutableLiveData<List<Recipe>?>()
    val recipeList: LiveData<List<Recipe>?>
        get() = _recipeList

    private val _bestRecipeList = MutableLiveData<List<Recipe>?>()
    val bestRecipeList: LiveData<List<Recipe>?>
        get() = _bestRecipeList

    private val _favoriteRecipeList = MutableLiveData<List<Recipe>?>()
    val favoriteRecipeList: LiveData<List<Recipe>?>
        get() = _favoriteRecipeList

    fun loadData() = onMainDispatcher {
        _recipeList.postValue(loadAllRecipeUseCase().subList(0, 10))
        _bestRecipeList.postValue(
            loadAllRecipeUseCase(
                orderBy = RecipeSortOption.LIKES,
                isGridType = true
            ).subList(0, 10)
        )
        _favoriteRecipeList.postValue(
            loadFavoriteRecipeUseCase(
                category = RecipeCategory.ALL,
                isGridType = true
            )
        )
    }

    fun readRecipe(recipe: Recipe) = onMainDispatcher {
        insertLatestReadRecipeUseCase(recipe)
        deleteOldestReadRecipeUseCase()
    }
}