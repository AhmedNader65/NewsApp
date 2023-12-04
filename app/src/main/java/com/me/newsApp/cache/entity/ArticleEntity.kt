package com.me.newsApp.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "article",
)
class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val source: String,
)