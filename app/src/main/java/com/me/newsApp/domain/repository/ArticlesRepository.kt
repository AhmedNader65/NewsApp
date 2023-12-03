package com.me.newsApp.domain.repository

import com.me.newsApp.domain.model.Article

interface ArticlesRepository {
    suspend fun getTopHeadlines(): List<Article>
    suspend fun fetchTopHeadlines(): List<Article>
    suspend fun storeHeadlines(articles: List<Article>)
}