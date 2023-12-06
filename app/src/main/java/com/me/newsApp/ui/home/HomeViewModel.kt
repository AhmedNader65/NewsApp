package com.me.newsApp.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.newsApp.domain.model.AppErrors
import com.me.newsApp.domain.model.Article
import com.me.newsApp.domain.usecase.FetchTopHeadlines
import com.me.newsApp.domain.usecase.GetTopHeadlines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val fetchTopHeadlines: FetchTopHeadlines,
    private val getTopHeadlines: GetTopHeadlines
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Idle)
    val homeUiState: StateFlow<HomeUIState> = _homeUiState.asStateFlow()

    private val _articlesFlow = MutableStateFlow<List<Article>>(emptyList())
    val articlesFlow: StateFlow<List<Article>> get() = _articlesFlow

    fun fetchArticles() {
        viewModelScope.launch {
            _homeUiState.emit(HomeUIState.Loading)
            fetchTopHeadlines.uiState()
        }
        viewModelScope.launch {
            _articlesFlow.emitAll(getTopHeadlines())
        }
    }

    private suspend fun FetchTopHeadlines.uiState() {
        return withContext(Dispatchers.IO) {
            try {
                invoke()
                _homeUiState.value = HomeUIState.Idle
            } catch (e: Exception) {
                _homeUiState.value = HomeUIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}