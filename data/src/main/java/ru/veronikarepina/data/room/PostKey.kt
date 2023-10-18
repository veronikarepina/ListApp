package ru.veronikarepina.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.veronikarepina.data.utils.Constants

@Entity(tableName = Constants.REMOTE_KEYS_TABLE)
data class PostKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prev: Int?,
    val next: Int?
)