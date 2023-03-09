package com.jymun.harusekki.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.memo.Memo
import com.jymun.harusekki.domain.ingredient.SearchIngredientByTitleUseCase
import com.jymun.harusekki.domain.memo.DeleteMemoUseCase
import com.jymun.harusekki.domain.memo.InsertMemoUseCase
import com.jymun.harusekki.domain.memo.LoadMemoUseCase
import com.jymun.harusekki.ui.base.BaseViewModel
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val loadMemoUseCase: LoadMemoUseCase,
    private val insertMemoUseCase: InsertMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase,
    private val searchIngredientByTitleUseCase: SearchIngredientByTitleUseCase
) : BaseViewModel(dispatcherProvider) {

    private val _memoList = MutableLiveData<List<Memo>?>()
    val memoList: LiveData<List<Memo>?>
        get() = _memoList

    private val _ingredientList = MutableLiveData<List<Ingredient>?>()
    val ingredientList: LiveData<List<Ingredient>?>
        get() = _ingredientList

    val memoCount = memoList.switchMap {
        MutableLiveData(it?.size ?: 0)
    }
    val selectedMemoCount = memoList.switchMap {
        MutableLiveData(
            it?.filter { it.isChecked }?.size ?: 0
        )
    }

    fun loadAllMemo() = onMainDispatcher {
        _memoList.postValue(
            loadMemoUseCase()
        )
    }

    fun insertMemo(memo: Memo) = onMainDispatcher {
        insertMemoUseCase(memo)
        loadAllMemo()
    }

    fun deleteMemo(memo: Memo) = onMainDispatcher {
        deleteMemoUseCase(memo)
        loadAllMemo()
    }

    fun searchIngredient(keyword: String) = onMainDispatcher {
        _ingredientList.postValue(
            searchIngredientByTitleUseCase(keyword)
        )
    }
}
