package com.me.newsApp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.me.newsApp.R
import com.me.newsApp.domain.model.Article
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DetailsScreen(
    navController: NavController,
    title: String?,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val article by viewModel.articleFlow.collectAsState()
    article?.let {
        ArticleDetails(article = it)
    }
}

@Composable
fun ArticleDetails(article: Article) {
    val painter =
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage)
                .size(
                    Size.ORIGINAL
                ) // Set the target size to load the image at.
                .build()
        )

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = article.title,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (painter.state is AsyncImagePainter.State.Success) {
            Image(painter = painter, contentDescription = "")
        } else {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .background(Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = article.description,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(
                R.string.by_author,
                article.author
            ),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale("en")).format(article.publishedAt),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = article.content,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.source, article.source),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
