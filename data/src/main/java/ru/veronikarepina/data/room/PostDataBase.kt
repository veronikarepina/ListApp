package ru.veronikarepina.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.veronikarepina.data.utils.Constants.POSTS_DATABASE
import ru.veronikarepina.domain.model.Post


@Database(entities = [Post::class, PostKey::class], version = 2, exportSchema = false)
abstract class PostDataBase: RoomDatabase() {

    abstract fun postsDao(): PostDao

    companion object {

        fun getInstance(context: Context): PostDataBase {
            return Room.databaseBuilder(
                context,
                PostDataBase::class.java,
                POSTS_DATABASE
            ).build()
        }
    }
}