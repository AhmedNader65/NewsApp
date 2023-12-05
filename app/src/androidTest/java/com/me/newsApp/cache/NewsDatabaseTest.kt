package com.me.newsApp.cache

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.me.newsApp.cache.NewsDatabase
import com.me.newsApp.cache.dao.ArticleDao
import com.me.newsApp.cache.entity.ArticleEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class NewsDatabaseTest {

    private lateinit var db: NewsDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        articleDao = db.articleDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testArticleDao() = runBlocking {
        // Create a sample article
        val article = ArticleEntity(
            author = "Author",
            title = "Title",
            description = "Description",
            url = "URL",
            urlToImage = "URL to Image",
            publishedAt = "Published At",
            content = "Content",
            source = "Source"
        )

        // Insert the article into the database
        articleDao.insert(article)

        // Retrieve the article from the database
        val retrievedArticle = articleDao.getArticleByTitle("Title").first()
        println("retrievedArticle: $retrievedArticle")

        println("article: $article")
        // Assert that the retrieved article is the same as the one you inserted
        assertEquals(article, retrievedArticle)

        // Retrieve all articles from the database
        val allArticles = articleDao.getArticles().first()

        // Assert that the list contains the inserted article
        assertEquals(listOf(article), allArticles)

        // Delete all articles from the database
        articleDao.deleteAll()

        // Assert that the database is empty
        val emptyList = articleDao.getArticles().first()
        assertEquals(emptyList, listOf<ArticleEntity>())
    }
}