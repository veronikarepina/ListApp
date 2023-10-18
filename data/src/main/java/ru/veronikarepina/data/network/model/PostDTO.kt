package ru.veronikarepina.data.network.model


data class PostDTO(
    val body: String,
    val id: Int,
    val title: String,
    val user_id: Int
)