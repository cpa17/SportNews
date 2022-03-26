package com.example.soccernews.data

import com.example.soccernews.newsfeed.Post

data class NewsResponse(
    val articles: List<Post>? = null,
    val status: String? = null,
    val totalResults: Int
)