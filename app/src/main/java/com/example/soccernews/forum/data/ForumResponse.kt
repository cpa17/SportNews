package com.example.soccernews.forum.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

class ForumResponse {
    @Entity(tableName = "posts")
    class Post : Serializable{
        @PrimaryKey(autoGenerate = true)
        var id:Int? = null
        @ColumnInfo(name = "title")
        var title: String? = null
        @ColumnInfo(name = "content")
        var content: String? = null
    }
}