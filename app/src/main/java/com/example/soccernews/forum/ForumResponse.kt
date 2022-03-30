package com.example.soccernews.forum

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ForumResponse {
    @SerializedName("articles")
    var articles: ArrayList<ForumResponse.Post>? = null

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