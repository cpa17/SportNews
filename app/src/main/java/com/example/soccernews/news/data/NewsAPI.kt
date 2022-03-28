package com.example.soccernews.news.data

import com.example.soccernews.util.Constants.Companion.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    companion object {
        const val EVERYTHING = "v2/everything"
        const val TOP_HEADLINES = "v2/top-headlines"
    }

    @GET(TOP_HEADLINES)
    fun breakingNews(
        @Query("page") page: Int,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apikey") apikey: String = API_KEY
    ): Single<NewsResponse>
}