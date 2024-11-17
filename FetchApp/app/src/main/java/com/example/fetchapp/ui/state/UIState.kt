package com.example.fetchapp.ui.state

/**
 * Represent the state of the view in a structured, type safe way
 */
sealed class UIState<out T> {
    data object Loading: UIState<Nothing>()
    data class Success<T>(val data: T): UIState<T>()
    data class Error(val msg: String) : UIState<Nothing>()
}