package com.me.newsApp.data.mappers

import com.me.newsApp.network.model.ApiArticle

fun ApiArticle.toArticle() = com.me.newsApp.domain.model.Article(
    author = author ?: "",
    title = title ?: "",
    description = description ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt ?: "",
    content = content ?: "",
    source = source?.name ?: ""
)