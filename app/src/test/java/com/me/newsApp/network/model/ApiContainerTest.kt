package com.me.newsApp.network.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ApiContainerTest {

    @Test
    fun apiContainerProperties_areCorrect() {
        val apiArticle = ApiArticle(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "Url",
            urlToImage = "UrlToImage",
            publishedAt = "PublishedAt",
            content = "Content",
            source = ApiArticleSource("SourceId", "SourceName")
        )
        val apiContainer = ApiContainer(
            status = "ok",
            totalResults = 1,
            num_results = 1,
            articles = listOf(apiArticle)
        )

        assertEquals("ok", apiContainer.status)
        assertEquals(1, apiContainer.totalResults)
        assertEquals(1, apiContainer.num_results)
        assertEquals(listOf(apiArticle), apiContainer.articles)
    }
}