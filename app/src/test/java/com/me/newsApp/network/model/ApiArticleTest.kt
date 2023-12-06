package com.me.newsApp.network.model

import org.junit.Assert.*

import org.junit.Assert.assertEquals
import org.junit.Test

class ApiArticleTest {

    @Test
    fun apiArticleProperties_areCorrect() {
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

        assertEquals("Author", apiArticle.author)
        assertEquals("Title", apiArticle.title)
        assertEquals("Description", apiArticle.description)
        assertEquals("Url", apiArticle.url)
        assertEquals("UrlToImage", apiArticle.urlToImage)
        assertEquals("PublishedAt", apiArticle.publishedAt)
        assertEquals("Content", apiArticle.content)
        assertEquals(ApiArticleSource("SourceId", "SourceName"), apiArticle.source)
    }
}