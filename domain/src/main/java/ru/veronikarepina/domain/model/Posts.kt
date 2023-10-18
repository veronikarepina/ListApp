package ru.veronikarepina.domain.model

data class Posts (
    val `data`: List<Post>,
    val limit: Int,
    val page: Int,
    val total: Int

)