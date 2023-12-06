package com.me.newsApp.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class ArticleTest {

    @Test
    fun articleProperties_areCorrect() {
        val article = Article(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "Url",
            urlToImage = "UrlToImage",
            publishedAt = Date(),
            content = "Content",
            source = "Source"
        )

        assertEquals("Author", article.author)
        assertEquals("Title", article.title)
        assertEquals("Description", article.description)
        assertEquals("Url", article.url)
        assertEquals("UrlToImage", article.urlToImage)
        assertEquals("Content", article.content)
        assertEquals("Source", article.source)
    }
}