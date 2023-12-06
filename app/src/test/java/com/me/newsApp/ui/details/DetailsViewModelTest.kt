package com.me.newsApp.ui.details

import androidx.lifecycle.SavedStateHandle
import com.me.newsApp.common.toDate
import com.me.newsApp.domain.model.Article
import com.me.newsApp.domain.usecase.GetArticleByTitle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var detailsViewModel: DetailsViewModel
    private val mockGetArticleByTitle = Mockito.mock(GetArticleByTitle::class.java)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        detailsViewModel = DetailsViewModel(SavedStateHandle(mapOf("title" to "Test Article")), mockGetArticleByTitle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun init_whenSuccessful_updatesArticleFlow() = testDispatcher.runBlockingTest {
        val article = Article(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "Url",
            urlToImage = "UrlToImage",
            publishedAt = "2022-03-01T12:00:00Z".toDate(),
            content = "Content",
            source = "SourceName"
        )
        Mockito.`when`(mockGetArticleByTitle.invoke("Test Article")).thenReturn(flowOf(article))
        detailsViewModel.getData()
        advanceUntilIdle() // Advances the virtual clock until no jobs are active

        val fetchedArticle = detailsViewModel.articleFlow.value
        assertEquals(article, fetchedArticle)
    }
}