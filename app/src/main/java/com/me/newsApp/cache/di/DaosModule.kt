package com.me.newsApp.cache.di

import com.me.newsApp.cache.NewsDatabase
import com.me.newsApp.cache.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesShiftDao(
        database: NewsDatabase,
    ): ArticleDao = database.articleDao()
}