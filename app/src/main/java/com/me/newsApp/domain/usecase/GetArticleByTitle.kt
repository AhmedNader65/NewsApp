package com.me.newsApp.domain.usecase

import com.me.newsApp.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticleByTitle @Inject constructor(private val articlesRepository: ArticlesRepository) {
    suspend operator fun invoke(title: String) = articlesRepository.getArticleByTitle(title)
}