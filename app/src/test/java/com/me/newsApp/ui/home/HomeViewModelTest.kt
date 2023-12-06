package com.me.newsApp.ui.home

import com.me.newsApp.common.toDate
import com.me.newsApp.domain.model.AppErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import com.me.newsApp.domain.model.Article
import com.me.newsApp.domain.model.ServerError
import com.me.newsApp.domain.usecase.FetchTopHeadlines
import com.me.newsApp.domain.usecase.GetTopHeadlines
import com.me.newsApp.network.model.ApiArticle
import com.me.newsApp.network.model.ApiArticleSource
import com.me.newsApp.ui.home.HomeUIState
import com.me.newsApp.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private val mockFetchTopHeadlines = Mockito.mock(FetchTopHeadlines::class.java)
    private val mockGetTopHeadlines = Mockito.mock(GetTopHeadlines::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        homeViewModel = HomeViewModel(mockFetchTopHeadlines, mockGetTopHeadlines)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchArticles_whenSuccessful_updatesUiStateToIdle() = runTest {
        val articles = listOf(
            Article(
                author = "Author",
                title = "Title",
                description = "Description",
                url = "Url",
                urlToImage = "UrlToImage",
                publishedAt = "2022-03-01T12:00:00Z".toDate(),
                content = "Content",
                source = "SourceName"
            )
        )
        Mockito.`when`(mockGetTopHeadlines.invoke()).thenReturn(flowOf(articles))
        homeViewModel.fetchArticles()
        advanceUntilIdle() // Advances the virtual clock until no jobs are active

        val uiState = homeViewModel.homeUiState.first()
        assertEquals(HomeUIState.Idle, uiState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchArticles_whenError_updatesUiStateToError() = runTest {
        Mockito.`when`(mockGetTopHeadlines.invoke()).thenReturn(flowOf(listOf()))
        Mockito.`when`(mockFetchTopHeadlines.invoke()).thenThrow(ServerError("Network error"))

        homeViewModel.fetchArticles()
        advanceUntilIdle() // Advances the virtual clock until no jobs are active

        val uiState = homeViewModel.homeUiState.first()
        assertEquals(HomeUIState.Error("Network error"), uiState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchArticles_whenSuccessful_updatesArticlesFlow() = runTest {
        val articles = listOf(
            Article(
                author = "Author",
                title = "Title",
                description = "Description",
                url = "Url",
                urlToImage = "UrlToImage",
                publishedAt = "2022-03-01T12:00:00Z".toDate(),
                content = "Content",
                source = "SourceName"
            )
        )
        Mockito.`when`(mockGetTopHeadlines.invoke()).thenReturn(flowOf(articles))
        homeViewModel.fetchArticles()
        advanceUntilIdle() // Advances the virtual clock until no jobs are active

        val fetchedArticles = homeViewModel.articlesFlow.first()
        assertEquals(articles, fetchedArticles)
    }
}