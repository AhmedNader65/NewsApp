package com.me.newsApp.ui.home



sealed interface HomeUIState {
    object Idle : HomeUIState
    object Loading : HomeUIState
    data class  Success(val text: String="") : HomeUIState
    data class Error(val message: String) : HomeUIState
}
