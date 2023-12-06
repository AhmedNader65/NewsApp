package com.me.newsApp.domain.usecase

import com.me.newsApp.domain.repository.ArticlesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetTopHeadlinesTest {

    private lateinit var getTopHeadlines: GetTopHeadlines
    private val mockArticlesRepository = Mockito.mock(ArticlesRepository::class.java)

    @Before
    fun setUp() {
        getTopHeadlines = GetTopHeadlines(mockArticlesRepository)
    }

    @Test
    fun invoke_callsGetTopHeadlines() {
        runBlocking {
            getTopHeadlines.invoke()
            Mockito.verify(mockArticlesRepository).getTopHeadlines()
        }
    }
}