package com.jymun.harusekki.ui.search_result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.domain.recipe.*
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
    private val searchRecipeByIngredientUseCase: SearchRecipeByIngredientUseCase,
    private val insertLatestReadRecipeUseCase: InsertLatestReadRecipeUseCase,
    private val deleteOldestReadRecipeUseCase: DeleteOldestReadRecipeUseCase
) : BaseViewModel(dispatcherProvider) {

    private val searchMode = MutableLiveData<SearchMode>()

    fun updateSearchMode(mode: SearchMode) = searchMode.postValue(mode)

    fun updateSortOption(sortOption: RecipeSortOption) = searchMode.value?.let {
        it.sortOption = sortOption
        searchMode.postValue(it)
    }

    val searchResult = searchMode.switchMap { mode ->
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

    fun readRecipe(recipe: Recipe) = onMainDispatcher {
        insertLatestReadRecipeUseCase(recipe)
        deleteOldestReadRecipeUseCase()
    }
}