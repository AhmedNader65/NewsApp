package com.me.newsApp.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.me.newsApp.cache.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Transaction
    @Query("SELECT * FROM article")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entities: ArticleEntity?)

    @Query("DELETE FROM article")
    suspend fun deleteAll()
}