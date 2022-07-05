package com.harusekki.jymun.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harusekki.jymun.data.model.TestModel

class MemoViewModel : ViewModel() {

    private val _itemList = MutableLiveData<List<TestModel>>()
    val itemList: LiveData<List<TestModel>>
        get() = _itemList

    private val data = mutableListOf<TestModel>()

    fun addItem() = with(data) {
        add(TestModel(size + 1, "test item"))
        _itemList.value = this
    }
}