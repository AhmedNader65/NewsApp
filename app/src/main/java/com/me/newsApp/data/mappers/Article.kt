package com.me.newsApp.data.mappers

import com.me.newsApp.cache.entity.ArticleEntity
import com.me.newsApp.common.toDate
import com.me.newsApp.domain.model.Article
import com.me.newsApp.network.model.ApiArticle

fun ApiArticle.toEntity() = ArticleEntity(
    author = author ?: "",
    title = title ?: "",
    description = description ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt ?: "",
    content = content ?: "",
    source = source?.name ?: ""
)

fun ArticleEntity.toArticle() = Article(
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt.toDate(),
    content = content,
    source = source
)