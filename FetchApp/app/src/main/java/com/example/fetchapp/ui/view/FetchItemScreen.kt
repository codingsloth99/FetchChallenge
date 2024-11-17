package com.example.fetchapp.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fetchapp.data.Item
import com.example.fetchapp.ui.state.UIState
import com.example.fetchapp.viewmodel.ItemViewModel

@Composable
fun FetchItemScreen (
    navController: NavController,
    viewModel: ItemViewModel = hiltViewModel()
) {

    val uiState: UIState<Map<Int, List<Item>>> by viewModel.itemState.collectAsState()

    when (uiState) {
        is UIState.Error -> {
            FetchErrorView((uiState as UIState.Error).msg)
        }
        is UIState.Success -> {
            FetchListView((uiState as UIState.Success<Map<Int, List<Item>>>).data)
        }
        is UIState.Loading -> {
            FetchLoadingView()
        }
    }

}