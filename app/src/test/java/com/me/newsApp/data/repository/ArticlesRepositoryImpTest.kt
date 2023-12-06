package com.me.newsApp.data.repository

import com.me.newsApp.cache.dao.ArticleDao
import com.me.newsApp.data.mappers.toArticle
import com.me.newsApp.data.mappers.toEntity
import com.me.newsApp.network.NewsApi
import com.me.newsApp.network.model.ApiArticle
import com.me.newsApp.network.model.ApiArticleSource
import com.me.newsApp.network.model.ApiContainer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ArticlesRepositoryImpTest {

    private lateinit var articlesRepository: ArticlesRepositoryImp
    private val mockApi = Mockito.mock(NewsApi::class.java)
    private val mockDao = Mockito.mock(ArticleDao::class.java)

    @Before
    fun setUp() {
        articlesRepository = ArticlesRepositoryImp(mockApi, mockDao)
    }

    @Test
    fun getTopHeadlines_returnsExpectedArticles() = runBlocking {
        val expectedArticles = listOf(
            ApiArticle(
                author = "Author",
                title = "Title",
                description = "Description",
                url = "Url",
                urlToImage = "UrlToImage",
                publishedAt = "2022-03-01T12:00:00Z",
                content = "Content",
                source = ApiArticleSource("SourceID", "Source")
            )
        )
        Mockito.`when`(mockDao.getArticles())
            .thenReturn(flowOf(expectedArticles.map { it.toEntity() }))

        val actualArticles = articlesRepository.getTopHeadlines().first()
        assertEquals(expectedArticles.map { it.toEntity().toArticle() }, actualArticles)
    }

    @Test
    fun getArticleByTitle_returnsExpectedArticle() = runBlocking {
        val expectedArticle = ApiArticle(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "Url",
            urlToImage = "UrlToImage",
            publishedAt = "2022-03-01T12:00:00Z",
            content = "Content",
            source = ApiArticleSource("SourceID", "Source")
        )
        Mockito.`when`(mockDao.getArticleByTitle(expectedArticle.title!!))
            .thenReturn(flowOf(expectedArticle.toEntity()))

        val actualArticle = articlesRepository.getArticleByTitle(expectedArticle.title!!).first()

        assertEquals(expectedArticle.toEntity().toArticle(), actualArticle)
    }

    @Test
    fun fetchTopHeadlines_storesFetchedArticles() = runBlocking {
        val fetchedArticles = listOf(
            ApiArticle(
                author = "Author",
                title = "Title",
                description = "Description",
                url = "Url",
                urlToImage = "UrlToImage",
                publishedAt = "2022-03-01T12:00:00Z",
                content = "Content",
                source = ApiArticleSource("SourceID", "Source")
            )
        )
        Mockito.`when`(mockApi.getTopHeadlines()).thenReturn(
            ApiContainer(
                articles = fetchedArticles,
                status = "ok",
                totalResults = 1,
                num_results = 1
            )
        )

        articlesRepository.fetchTopHeadlines()

        Mockito.verify(mockDao).insert(*fetchedArticles.map { it.toEntity() }.toTypedArray())
    }
}