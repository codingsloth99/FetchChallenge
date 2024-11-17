package com.example.fetchapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapp.data.Item
import com.example.fetchapp.network.ApiCallExecutor
import com.example.fetchapp.repo.ItemRepository
import com.example.fetchapp.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val executor: ApiCallExecutor
) : ViewModel() {

    private val _itemState = MutableStateFlow<UIState<Map<Int, List<Item>>>>(UIState.Loading)
    val itemState: StateFlow<UIState<Map<Int, List<Item>>>> = _itemState

    init {
        fetchItemList()
    }


    private fun fetchItemList() {
        executor.executeApiCall(
            scope = viewModelScope,
            targetState = _itemState,
            apiCall = { itemRepository.fetchItem() },
            onSuccessMap = {data -> data}
        )
    }
}