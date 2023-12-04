package com.me.newsApp.domain.usecase

import com.me.newsApp.domain.model.Article
import com.me.newsApp.domain.repository.ArticlesRepository
import javax.inject.Inject

class FetchTopHeadlines @Inject constructor(private val articlesRepository: ArticlesRepository) {
    suspend operator fun invoke() {
         articlesRepository.fetchTopHeadlines()
    }
}