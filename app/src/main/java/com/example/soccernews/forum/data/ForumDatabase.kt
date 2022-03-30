package com.example.soccernews.forum.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ForumResponse.Post::class], version = 1, exportSchema = false)
abstract class ForumDatabase : RoomDatabase() {

    companion object {
        var forumDatabase: ForumDatabase? = null


        @Synchronized
        fun getDatabase(context: Context): ForumDatabase {
            if (forumDatabase == null) {
                forumDatabase = Room.databaseBuilder(
                    context
                    , ForumDatabase::class.java
                    , "posts.db"
                ).build()
            }
            return forumDatabase!!
        }
    }

    abstract fun forumDao(): ForumDao
}