package com.jymun.harusekki.ui.search_recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jymun.harusekki.data.model.recipe.SearchKeyword
import com.jymun.harusekki.domain.recipe.search_keyword.AddSearchKeywordUseCase
import com.jymun.harusekki.domain.recipe.search_keyword.DeleteAllSearchKeywordsUseCase
import com.jymun.harusekki.domain.recipe.search_keyword.DeleteSearchKeywordUseCase
import com.jymun.harusekki.domain.recipe.search_keyword.LoadAllSearchKeywordsUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchRecipeViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val addSearchKeywordUseCase: AddSearchKeywordUseCase,
    private val deleteSearchKeywordUseCase: DeleteSearchKeywordUseCase,
    private val loadAllSearchKeywordsUseCase: LoadAllSearchKeywordsUseCase,
    private val deleteAllSearchKeywordsUseCase: DeleteAllSearchKeywordsUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _searchKeywordList = MutableLiveData<List<SearchKeyword>?>()
    val searchKeywordList: LiveData<List<SearchKeyword>?>
        get() = _searchKeywordList

    fun loadAllSearchKeywords() = onMainDispatcher {
        _searchKeywordList.postValue(
            loadAllSearchKeywordsUseCase()
        )
    }

    fun deleteAllSearchKeywords() = onMainDispatcher {
        _searchKeywordList.postValue(null)
        deleteAllSearchKeywordsUseCase()
    }

    fun addSearchKeyword(searchKeyword: SearchKeyword) = onMainDispatcher {
        addSearchKeywordUseCase(searchKeyword)
        loadAllSearchKeywords()
    }

    fun deleteSearchKeyword(searchKeyword: SearchKeyword) = onMainDispatcher {
        deleteSearchKeywordUseCase(searchKeyword)
//        loadAllSearchKeywords()
    }
}