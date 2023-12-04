package com.me.newsApp.domain.repository

import com.me.newsApp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getTopHeadlines(): Flow<List<Article>>
    suspend fun getArticleByTitle(title:String): Flow<Article>
    suspend fun fetchTopHeadlines()
    suspend fun storeHeadlines(articles: List<Article>)
}