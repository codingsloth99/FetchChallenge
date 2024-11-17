package com.example.fetchapp.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

/**
 * Generic Error View that is reusable
 */
@Composable
fun FetchErrorView(
    errorMsg: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = errorMsg,
            color = MaterialTheme.colorScheme.error,
            fontSize = 16.sp
        )
    }
}