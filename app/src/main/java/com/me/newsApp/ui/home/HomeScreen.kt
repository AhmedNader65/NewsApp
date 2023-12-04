package com.me.newsApp.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.me.newsApp.domain.model.Article
import com.me.newsApp.ui.theme.components.LoadingWheel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchArticles()
    })
    Box {
        HomeContent(
            viewModel = viewModel,
            onArticleClick = {
                navController.navigate("article/${it.title}")
            }
        )
    }
    HandleState(viewModel)

}

@Composable
fun HomeContent(viewModel: HomeViewModel, onArticleClick: (Article) -> Unit) {
    val articles by viewModel.articlesFlow.collectAsState()

    LazyColumn {
        items(articles.size) { index ->
            ArticleItem(
                article = articles[index],
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
fun ArticleItem(article: Article, onArticleClick: (Article) -> Unit) {
    val painter =
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage)
                .size(
                    coil.size.Size.ORIGINAL
                ) // Set the target size to load the image at.
                .build()
        )
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onArticleClick(article) }
        ) {
            if (painter.state is AsyncImagePainter.State.Success) {
                Image(painter = painter, contentDescription = "")
            } else {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .background(Color.Gray)
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                text = article.title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
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