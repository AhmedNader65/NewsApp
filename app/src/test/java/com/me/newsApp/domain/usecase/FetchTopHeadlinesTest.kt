package com.me.newsApp.domain.usecase

import com.me.newsApp.domain.repository.ArticlesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FetchTopHeadlinesTest {

    private lateinit var fetchTopHeadlines: FetchTopHeadlines
    private val mockArticlesRepository = Mockito.mock(ArticlesRepository::class.java)

    @Before
    fun setUp() {
        fetchTopHeadlines = FetchTopHeadlines(mockArticlesRepository)
    }

    @Test
    fun invoke_callsFetchTopHeadlines() = runBlocking {
        fetchTopHeadlines.invoke()

        Mockito.verify(mockArticlesRepository).fetchTopHeadlines()
    }
}