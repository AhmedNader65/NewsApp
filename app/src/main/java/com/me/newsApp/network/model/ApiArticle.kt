package com.me.newsApp.network.model

import com.google.gson.annotations.SerializedName


data class ApiArticle(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val source: ApiArticleSource?,
)