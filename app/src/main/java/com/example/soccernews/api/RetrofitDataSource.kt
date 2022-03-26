package com.example.soccernews.api

import com.example.soccernews.data.NewsAPI
import com.example.soccernews.data.NewsResponse
import com.example.soccernews.newsfeed.Post
import com.example.soccernews.util.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitDataSource: RemoteDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api = retrofit.create(NewsAPI::class.java)


    override suspend fun getBreakingNews(): Response<NewsResponse> {
        return api.getBreakingNews()
    }
}