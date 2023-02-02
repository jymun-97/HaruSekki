package com.jymun.harusekki.ui.search_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jymun.harusekki.domain.recipe.LoadAllRecipeUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByIngredientUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByTitleUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadAllRecipeUseCase: LoadAllRecipeUseCase,
    private val searchRecipeByTitleUseCase: SearchRecipeByTitleUseCase,
    private val searchRecipeByIngredientUseCase: SearchRecipeByIngredientUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _searchMode = MutableLiveData<SearchMode>()
    val searchMode: LiveData<SearchMode>
        get() = _searchMode

    fun updateSearchMode(mode: SearchMode) = _searchMode.postValue(mode)

    fun updateSortOption(sortOption: RecipeSortOption) = _searchMode.value?.let {
        it.sortOption = sortOption
        _searchMode.postValue(it)
    }

    fun updateCategory(category: RecipeCategory) = _searchMode.value?.let {
        it.category = category
        _searchMode.postValue(it)
    }

    val searchResult = _searchMode.switchMap { mode ->
        liveData {
            emit(
                RecipeCategory.values().map { category ->
                    when (mode) {
                        is SearchMode.ByTitle -> searchRecipeByTitleUseCase(
                            keyword = mode.keyword,
                            orderBy = mode.sortOption,
                            category = category,
                            refreshFlag = false
                        ).take(10)

                        // todo. ingredient mode

                        // todo. favorite mode

                        else -> loadAllRecipeUseCase(
                            orderBy = mode.sortOption,
                            category = category,
                            refreshFlag = false,
                            isGridType = false
                        ).take(10)
                    }
                }
            )
        }
    }
}