package com.me.newsApp.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

fun String.toDate(): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    format.timeZone = TimeZone.getTimeZone("UTC")

    try {
        return format.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}