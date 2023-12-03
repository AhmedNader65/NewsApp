package com.me.newsApp.domain.model

import com.google.gson.annotations.SerializedName


data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val source: String,
)