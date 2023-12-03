package com.me.newsApp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.newsApp.domain.model.AppErrors
import com.me.newsApp.domain.usecase.FetchTopHeadlines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchTopHeadlines: FetchTopHeadlines
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Idle)
    val homeUiState: StateFlow<HomeUIState> = _homeUiState.asStateFlow()

    fun fetchArticles() {
        viewModelScope.launch {
            _homeUiState.emit(HomeUIState.Loading)
            fetchTopHeadlines.uiState()
        }
    }

    private suspend fun FetchTopHeadlines.uiState() {
        return withContext(Dispatchers.IO) {
            try {
                val result = invoke()
                _homeUiState.value = HomeUIState.Idle
            } catch (e: AppErrors) {
                _homeUiState.value = HomeUIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}