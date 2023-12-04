package com.me.newsApp.ui.details

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.newsApp.domain.model.AppErrors
import com.me.newsApp.domain.model.Article
import com.me.newsApp.domain.usecase.FetchTopHeadlines
import com.me.newsApp.domain.usecase.GetArticleByTitle
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
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getArticleByTitle: GetArticleByTitle
) : ViewModel() {

    private val title: String = checkNotNull(savedStateHandle["title"])

    private val _articleFlow = MutableStateFlow<Article?>(null)
    val articleFlow: StateFlow<Article?> get() = _articleFlow

    init {
        viewModelScope.launch {
            _articleFlow.emitAll(getArticleByTitle(title = title))
        }
    }
}