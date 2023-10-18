package ru.veronikarepina.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.common.Resource
import ru.veronikarepina.data.room.PostDao
import ru.veronikarepina.data.room.PostKey
import ru.veronikarepina.domain.model.Post
import ru.veronikarepina.domain.repository.GetPagerRepository

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val initialPage: Int = 1,
    private val getPagerRepository: GetPagerRepository,
    private val postDao: PostDao
): RemoteMediator<Int, Post>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Post>
    ): MediatorResult {
         return try {

            val page = when (loadType){
                LoadType.APPEND -> {
                    val remoteKey = getLastKey(state)
                    remoteKey?.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosetKey(state)
                    remoteKey?.next?.minus(1) ?: initialPage
                }
            }

            val response = getPagerRepository.getPagerPosts(page = page, perPage = state.config.pageSize)
            val endOfPagination = response.data?.size!! < state.config.pageSize

             when (response) {
                is Resource.Success -> {
                    val body = response.data

                    if (loadType == LoadType.REFRESH){
                        postDao.deleteAllPosts()
                        postDao.deleteAllKeys()
                    }

                    val prev = if (page == initialPage) null else page.minus(1)
                    val next = if (endOfPagination) null else page.plus(1)

                    val list = body?.map {
                        PostKey(id = it.id, prev = prev, next = next)
                    }

                    list?.let {
                        postDao.insertAllKeys(list)
                        postDao.insertPosts(body)
                    }
                }

                is Resource.Error -> {
                    MediatorResult.Error(Exception())
                }

                else -> {}
            }

             if (response is Resource.Success) {
                 if (endOfPagination) {
                     MediatorResult.Success(true)
                 } else {
                     MediatorResult.Success(false)
                 }
             } else {
                 MediatorResult.Success(true)
             }

        } catch (e: Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getClosetKey(state: PagingState<Int, Post>): PostKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let { post ->
                postDao.getAllRemoteKeys(post.id)
            }
        }
    }

    private suspend fun getLastKey(state: PagingState<Int, Post>): PostKey? {
        return state.lastItemOrNull()?.let {
            postDao.getAllRemoteKeys(it.id)
        }
    }

}