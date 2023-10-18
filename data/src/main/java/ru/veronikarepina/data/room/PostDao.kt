package ru.veronikarepina.data.room

import androidx.paging.PagingSource
import androidx.room.*
import ru.veronikarepina.data.utils.Constants.POSTS_TABLE
import ru.veronikarepina.data.utils.Constants.REMOTE_KEYS_TABLE
import ru.veronikarepina.domain.model.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(list: List<Post>)

    @Query("SELECT * FROM $POSTS_TABLE")
    fun getAllPosts(): PagingSource<Int, Post>

    @Query("DELETE FROM $POSTS_TABLE")
    suspend  fun deleteAllPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKeys(list: List<PostKey>)

    @Query("SELECT * FROM $REMOTE_KEYS_TABLE WHERE id = :id")
    suspend fun getAllRemoteKeys(id: Int): PostKey?

    @Query("DELETE FROM $REMOTE_KEYS_TABLE")
    suspend fun deleteAllKeys()
}