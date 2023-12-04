package com.me.newsApp.data.repository

import com.me.newsApp.cache.dao.ArticleDao
import com.me.newsApp.data.mappers.toArticle
import com.me.newsApp.data.mappers.toEntity
import com.me.newsApp.domain.model.Article
import com.me.newsApp.domain.model.NetworkUnavailableError
import com.me.newsApp.domain.model.ServerError
import com.me.newsApp.domain.model.UnknownError
import com.me.newsApp.domain.repository.ArticlesRepository
import com.me.newsApp.network.NewsApi
import com.me.newsApp.network.model.NetworkUnavailableException
import com.me.newsApp.network.model.ServerException
import com.me.newsApp.network.model.UnkownException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val api: NewsApi,
    private val articleDao: ArticleDao
) : ArticlesRepository {
    override suspend fun getTopHeadlines(): Flow<List<Article>> =
        articleDao.getArticles().map { articleEntitiesFlow -> articleEntitiesFlow.map { it.toArticle() } }

    override suspend fun fetchTopHeadlines() {
        try {
            val response = api.getTopHeadlines()
            articleDao.insert(
                *response.articles?.map { it.toEntity() }?.toTypedArray() ?: emptyArray()
            )
        } catch (e: NetworkUnavailableException) {
            throw NetworkUnavailableError(message = e.message ?: "Network unavailable")
        } catch (e: ServerException) {
            throw ServerError("Error fetching top headlines")
        } catch (e: UnkownException) {
            throw UnknownError("Error fetching top headlines")
        }
    }

    override suspend fun storeHeadlines(articles: List<Article>) {
        TODO("Not yet implemented")
    }
}