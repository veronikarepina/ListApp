package ru.veronikarepina.domain.repository

import com.example.common.Resource
import ru.veronikarepina.domain.model.Post

interface GetPagerRepository {

    suspend fun getPagerPosts(page: Int, perPage: Int): Resource<List<Post>>
}