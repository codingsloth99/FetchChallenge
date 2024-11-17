package com.example.fetchapp.data

import androidx.compose.runtime.Immutable

@Immutable
data class Item(
    val listId: Int,
    val name: String?,
    val id: Int
)