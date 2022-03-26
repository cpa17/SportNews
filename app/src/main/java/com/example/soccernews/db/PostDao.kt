package com.example.soccernews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.soccernews.newsfeed.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Post): Long

    @Query("SELECT * FROM post")
    fun getAllArticles(): LiveData<List<Post>>

    @Delete
    suspend fun deleteArticle(article: Post)
}