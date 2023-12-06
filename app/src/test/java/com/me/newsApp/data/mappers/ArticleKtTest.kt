package com.me.newsApp.data.mappers

import com.me.newsApp.cache.entity.ArticleEntity
import com.me.newsApp.common.toDate
import com.me.newsApp.domain.model.Article
import com.me.newsApp.network.model.ApiArticle
import com.me.newsApp.network.model.ApiArticleSource
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleMapperTest {

    @Test
    fun apiArticleToEntity_mapsCorrectly() {
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

        val expectedEntity = ArticleEntity(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "Url",
            urlToImage = "UrlToImage",
            publishedAt = "PublishedAt",
            content = "Content",
            source = "SourceName"
        )

        val actualEntity = apiArticle.toEntity()

        assertEquals(expectedEntity, actualEntity)
    }

    @Test
    fun articleEntityToArticle_mapsCorrectly() {
        val articleEntity = ArticleEntity(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "Url",
            urlToImage = "UrlToImage",
            publishedAt = "PublishedAt",
            content = "Content",
            source = "SourceName"
        )

        val expectedArticle = Article(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "Url",
            urlToImage = "UrlToImage",
            publishedAt = "2022-03-01T12:00:00Z".toDate(),
            content = "Content",
            source = "SourceName"
        )

        val actualArticle = articleEntity.toArticle()

        assertEquals(expectedArticle, actualArticle)
    }
}