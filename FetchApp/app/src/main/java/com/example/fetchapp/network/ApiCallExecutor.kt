package com.example.fetchapp.network

import com.example.fetchapp.ui.state.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ApiCallExecutor  {

    // Function to execute API calls with shared error and success handling logic
    fun <T, R> executeApiCall(
        scope: CoroutineScope,
        targetState: MutableStateFlow<UIState<R>>,
        apiCall: suspend () -> Flow<ApiResult<T>>,
        onSuccessMap: (T) -> R
    ) {
        scope.launch {
            apiCall()
                .map { result ->
                    when (result) {
                        is ApiResult.Success -> UIState.Success(onSuccessMap(result.data))
                        is ApiResult.Error -> UIState.Error("An error occurred: ${result.e.message}")
                    }
                }
                .catch { e ->
                    targetState.value = UIState.Error("Unknown error occurred: ${e.message}")
                }
                .collect { state ->
                    targetState.value = state
                }
        }
    }
}