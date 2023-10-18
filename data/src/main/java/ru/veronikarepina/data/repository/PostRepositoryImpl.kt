package ru.veronikarepina.data.repository

import ru.veronikarepina.data.mappers.toDomain
import ru.veronikarepina.data.network.PostApi
import ru.veronikarepina.domain.model.Post
import ru.veronikarepina.domain.repository.GetPostsRepository

class PostRepositoryImpl(
    private val postApi: PostApi
    ): GetPostsRepository {

    override suspend fun getPosts(): List<Post> {
        return postApi.getPosts().body()?.data?.toDomain() ?: emptyList()
    }
}