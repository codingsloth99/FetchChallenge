package com.example.fetchapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.example.fetchapp.ui.view.FetchItemScreen
import kotlinx.serialization.Serializable

fun fetchNavGraph(navController: NavController) : NavGraph {
    return navController.createGraph(Screen.ItemFeed) {
        addFetchScreen(navController)
    }
}

private fun NavGraphBuilder.addFetchScreen(navController: NavController) = composable<Screen.ItemFeed> {
    FetchItemScreen(navController)
}

/**
 * defining the navigation route
 */
@Serializable
sealed class Screen {
    @Serializable
    object ItemFeed
}