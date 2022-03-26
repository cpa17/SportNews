package com.example.soccernews.newsfeed

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soccernews.data.Source
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)