package com.me.newsApp.cache.di

import android.content.Context
import androidx.room.Room
import com.me.newsApp.cache.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "news-database",
    ).fallbackToDestructiveMigration().build()
}