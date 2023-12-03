package com.me.newsApp.data.repository

import com.me.newsApp.data.mappers.toArticle
import com.me.newsApp.domain.model.Article
import com.me.newsApp.domain.model.NetworkUnavailableError
import com.me.newsApp.domain.model.ServerError
import com.me.newsApp.domain.model.UnknownError
import com.me.newsApp.domain.repository.ArticlesRepository
import com.me.newsApp.network.NewsApi
import com.me.newsApp.network.model.NetworkUnavailableException
import com.me.newsApp.network.model.ServerException
import com.me.newsApp.network.model.UnkownException
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val api: NewsApi
) : ArticlesRepository {
    override suspend fun getTopHeadlines(): List<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTopHeadlines(): List<Article> {
        try {
            val response = api.getTopHeadlines()
            return response.articles?.map { it.toArticle() } ?: emptyList()
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