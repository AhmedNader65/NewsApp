package com.me.newsApp.cache


import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.me.newsApp.cache.dao.ArticleDao
import com.me.newsApp.cache.entity.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = 3,
    exportSchema = true,
)

abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}