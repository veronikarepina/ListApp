package ru.veronikarepina.domain.usecase

import com.example.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.veronikarepina.domain.model.Post
import ru.veronikarepina.domain.repository.GetPostsRepository

class GetPostsUseCase(private val postsRepository: GetPostsRepository) {

    operator fun invoke(page: Int, perPage: Int) : Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading(null))
        try {
            val response = postsRepository.getPosts()
            emit(Resource.Success(data = response))

        }catch (e:Exception){
            emit(Resource.Error("error occurred"))
        }
    }
}
