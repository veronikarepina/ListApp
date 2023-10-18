package ru.veronikarepina.domain.repository

import ru.veronikarepina.domain.model.Post

interface GetPostsRepository {

    suspend fun getPosts(): List<Post>
}