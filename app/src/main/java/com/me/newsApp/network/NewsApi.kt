package com.me.newsApp.network

import com.me.newsApp.BuildConfig
import com.me.newsApp.network.model.ApiContainer
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(ApiConstants.TOP_ENDPOINT + "/{section}/{period}.json")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): ApiContainer
}