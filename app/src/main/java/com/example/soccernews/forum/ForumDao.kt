package com.example.soccernews.forum

import androidx.room.*

@Dao
interface ForumDao {

    @Query("SELECT * FROM posts ORDER BY id DESC")
    suspend fun getAllPosts() : List<ForumResponse.Post>

    @Query("SELECT * FROM posts WHERE id =:id")
    suspend fun getSpecificPost(id:Int) : ForumResponse.Post

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: ForumResponse.Post)

    @Delete
    suspend fun deletePost(post: ForumResponse.Post)

    @Query("DELETE FROM posts WHERE id =:id")
    suspend fun deleteSpecificPost(id:Int)

    @Update
    suspend fun updatePost(post: ForumResponse.Post)
}