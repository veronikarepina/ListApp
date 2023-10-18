package ru.veronikarepina.data.network.model


data class PostsList(
    val code: Int,
    val data: List<PostDTO>,
    val meta: Meta
)