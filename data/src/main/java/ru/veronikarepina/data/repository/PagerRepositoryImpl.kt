package ru.veronikarepina.data.repository

import com.example.common.Resource
import ru.veronikarepina.data.mappers.toDomain
import ru.veronikarepina.data.network.PostApi
import ru.veronikarepina.domain.model.Post
import ru.veronikarepina.domain.repository.GetPagerRepository

class PagerRepositoryImpl(
    private val postApi: PostApi
): GetPagerRepository{

    override suspend fun getPagerPosts(page: Int, perPage: Int): Resource<List<Post>> {
        return try {
            val response = postApi.getPostsPagination(page = page, perPage = perPage)

            if (response.isSuccessful) {
                val body = response.body()?.data?.toDomain()
                Resource.Success(body)
            } else {
                Resource.Error(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }

    }
}