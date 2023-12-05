package com.me.newsApp.cache.entity

import org.junit.Test
import kotlin.test.assertEquals

class ArticleEntityTest {

    @Test
    fun testArticleEntity() {
        val article = ArticleEntity(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "URL",
            urlToImage = "URL to Image",
            publishedAt = "Published At",
            content = "Content",
            source = "Source"
        )

        assertEquals("Author", article.author)
        assertEquals("Title", article.title)
        assertEquals("Description", article.description)
        assertEquals("URL", article.url)
        assertEquals("URL to Image", article.urlToImage)
        assertEquals("Published At", article.publishedAt)
        assertEquals("Content", article.content)
        assertEquals("Source", article.source)
    }
}