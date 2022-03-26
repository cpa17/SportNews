package com.example.soccernews.db

import android.content.Context
import androidx.room.*
import com.example.soccernews.newsfeed.Post

@Database(
    entities = [Post::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PostDatabase: RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object {
        @Volatile
        private var instance: PostDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostDatabase::class.java,
                "post_db.db"
            ).build()
    }
}