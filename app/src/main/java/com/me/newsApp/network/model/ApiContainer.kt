package com.me.newsApp.network.model

data class ApiContainer(
    val status: String?,
    val totalResults: Int?,
    val num_results: Int?,
    val articles: List<ApiArticle>?
)