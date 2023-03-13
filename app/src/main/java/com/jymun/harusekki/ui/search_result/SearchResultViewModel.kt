package com.jymun.harusekki.ui.search_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.domain.ingredient.LoadIngredientsInRefrigeratorUseCase
import com.jymun.harusekki.domain.recipe.*
import com.jymun.harusekki.domain.recipe.favorite.LoadFavoriteRecipeUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadAllRecipeUseCase: LoadAllRecipeUseCase,
    private val searchRecipeByTitleUseCase: SearchRecipeByTitleUseCase,
    private val searchRecipeByIngredientUseCase: SearchRecipeByIngredientUseCase,
    private val insertLatestReadRecipeUseCase: InsertLatestReadRecipeUseCase,
    private val deleteOldestReadRecipeUseCase: DeleteOldestReadRecipeUseCase,
    private val loadFavoriteRecipeUseCase: LoadFavoriteRecipeUseCase,
    private val loadIngredientsInRefrigeratorUseCase: LoadIngredientsInRefrigeratorUseCase
) : BaseViewModel(dispatcherProvider) {

    private val searchMode = MutableLiveData<SearchMode>()

    private val _ingredientList = MutableLiveData<List<Ingredient>?>()
    val ingredientList: LiveData<List<Ingredient>?>
        get() = _ingredientList

    fun updateSearchMode(mode: SearchMode) = searchMode.postValue(mode)

    fun updateSortOption(sortOption: RecipeSortOption) = searchMode.value?.let {
        it.sortOption = sortOption
        searchMode.postValue(it)
    }

    fun refresh() = searchMode.postValue(searchMode.value)

    val searchResult = searchMode.switchMap { mode ->
        val result = MutableLiveData<List<List<Recipe>>>()
        onMainDispatcher {
            result.postValue(withContext(dispatcherProvider.default) {
                RecipeCategory.values().map { category ->
                    when (mode) {
                        is SearchMode.ByTitle -> searchRecipeByTitleUseCase(
                            keyword = mode.keyword,
                            orderBy = mode.sortOption,
                            category = category,
                            refreshFlag = false
                        ).take(10)

                        is SearchMode.ByIngredient -> searchRecipeByIngredientUseCase(
                            ingredientList = ingredientList.value!!,
                            orderBy = mode.sortOption,
                            category = category,
                        ).take(10)

                        is SearchMode.Favorite -> loadFavoriteRecipeUseCase(
                            orderBy = mode.sortOption,
                            category = category
                        ).take(10)

                        else -> loadAllRecipeUseCase(
                            orderBy = mode.sortOption,
                            category = category,
                            refreshFlag = false,
                            isGridType = false
                        ).take(10)
                    }
                }
            })
        }
        result
    }

    fun readRecipe(recipe: Recipe) = onMainDispatcher {
        insertLatestReadRecipeUseCase(recipe)
        deleteOldestReadRecipeUseCase()
    }

    fun loadIngredientListInRefrigerator() = onMainDispatcher {
        _ingredientList.postValue(
            loadIngredientsInRefrigeratorUseCase()
        )
    }
}