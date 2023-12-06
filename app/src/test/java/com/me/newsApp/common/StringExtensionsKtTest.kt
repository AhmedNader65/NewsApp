package com.me.newsApp.common
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.TimeZone
import kotlin.test.assertEquals

class StringExtensionsTest {

    @Test
    fun testToDate() {
        val dateString = "2022-03-01T12:00:00Z"
        val expectedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.parse(dateString)

        val actualDate = dateString.toDate()

        assertEquals(expectedDate, actualDate)
    }
}