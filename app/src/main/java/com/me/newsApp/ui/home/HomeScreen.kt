package com.me.newsApp.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.me.newsApp.ui.theme.components.LoadingWheel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchArticles()
    })

    HandleState(viewModel)

}

@Composable
fun HandleState(viewModel: HomeViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        val state by viewModel.homeUiState.collectAsState()
        when (state) {
            is HomeUIState.Idle -> {}
            is HomeUIState.Loading -> {
                LoadingWheel(
                    modifier = Modifier.align(Alignment.Center),
                    contentDesc = "loading",
                )
            }
            is HomeUIState.Success -> {}
            is HomeUIState.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    (state as HomeUIState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}