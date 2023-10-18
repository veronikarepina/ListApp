package ru.veronikarepina.listapplication.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.*
import ru.veronikarepina.data.paging.PostRemoteMediator
import ru.veronikarepina.data.room.PostDao
import ru.veronikarepina.domain.repository.GetPagerRepository

class PostsViewModel(
    private val getPagerRepository: GetPagerRepository,
    private val postDao: PostDao
): ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 5),
        remoteMediator = PostRemoteMediator(getPagerRepository = getPagerRepository, postDao = postDao)
    ){
        postDao.getAllPosts()
    }.flow.cachedIn(viewModelScope)
}