package com.example.soccernews.api

import com.example.soccernews.data.NewsResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getBreakingNews(): Response<NewsResponse>
}