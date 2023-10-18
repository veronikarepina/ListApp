package ru.veronikarepina.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class Post(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val body: String,
    val user_id: Int
)
