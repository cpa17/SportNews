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
        @Query("pageSize") page: Int = 100,
        @Query("country") country: String = "de",
        @Query("category") category: String = "sports",
        @Query("apikey") apikey: String = API_KEY
    ): Single<NewsResponse>

    @GET(EVERYTHING)
    fun searchNews(
        @Query("q") searchTerm: String,
        @Query("language") language: String = "de",
        @Query("domains") domains: String = "Sport1.de, sport.de, fussballtransfers.com, spox.com, ruhr24.de, fcbinside.de, motorsport-total.com, kicker.de, eurosport.de",
        @Query("apikey") apikey: String = API_KEY
    ): Single<NewsResponse>
}