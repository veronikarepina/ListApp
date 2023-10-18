package ru.veronikarepina.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.veronikarepina.data.network.model.PostsList

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): Response<PostsList>

    @GET("posts")
    suspend fun getPostsPagination(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<PostsList>

}