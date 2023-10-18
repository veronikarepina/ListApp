package ru.veronikarepina.data.mappers

import ru.veronikarepina.data.network.model.PostDTO
import ru.veronikarepina.domain.model.Post

fun List<PostDTO>.toDomain(): List<Post> = this.map {
    Post(
        id = it.id,
        title = it.title,
        body = it.body,
        user_id = it.user_id
    )
}