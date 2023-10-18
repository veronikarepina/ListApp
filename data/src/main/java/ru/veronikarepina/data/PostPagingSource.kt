//package ru.veronikarepina.data
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import retrofit2.HttpException
//import ru.veronikarepina.data.mappers.toDomain
//import ru.veronikarepina.data.network.PostApi
//import ru.veronikarepina.domain.model.Post
//import java.io.IOException
//
//const val STARTING_INDEX = 1
//
//class PostPagingSource(private val postApi: PostApi) : PagingSource<Int, Post>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
//
//        val position = params.key ?: STARTING_INDEX
//
//        return try {
//            val data = postApi.getPosts()
//
//
//            LoadResult.Page(
//                data = data.body()?.data?.toDomain() ?: emptyList(),
//                prevKey = if (params.key == STARTING_INDEX) null else position - 1,
//                nextKey = if (data.body()?.data?.toDomain()?.isEmpty() ?: true) null else position + 1
//            )
//        } catch (e: IOException) {
//            LoadResult.Error(e)
//        } catch (e: HttpException) {
//            LoadResult.Error(e)
//        }
//
//
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//}