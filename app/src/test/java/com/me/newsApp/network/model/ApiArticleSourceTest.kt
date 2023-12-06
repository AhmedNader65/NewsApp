package com.me.newsApp.network.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ApiArticleSourceTest {

    @Test
    fun apiArticleSourceProperties_areCorrect() {
        val apiArticleSource = ApiArticleSource(
            id = "SourceId",
            name = "SourceName"
        )

        assertEquals("SourceId", apiArticleSource.id)
        assertEquals("SourceName", apiArticleSource.name)
    }
}