package com.me.newsApp.domain.usecase

import com.me.newsApp.domain.repository.ArticlesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetArticleByTitleTest {

    private lateinit var getArticleByTitle: GetArticleByTitle
    private val mockArticlesRepository = Mockito.mock(ArticlesRepository::class.java)

    @Before
    fun setUp() {
        getArticleByTitle = GetArticleByTitle(mockArticlesRepository)
    }

    @Test
    fun invoke_callsGetArticleByTitle(): Unit = runBlocking {
        val title = "Test Article"
        getArticleByTitle.invoke(title)

        Mockito.verify(mockArticlesRepository).getArticleByTitle(title)
    }
}