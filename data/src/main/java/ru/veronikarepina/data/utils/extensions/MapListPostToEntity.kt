package ru.veronikarepina.data.utils.extensions
//
//import ru.veronikarepina.data.local.PostEntity
//import ru.veronikarepina.domain.model.Post
//
//fun List<Post>.toEntityList(): List<PostEntity> = this.map { post ->
//    PostEntity(
//        id = post.id,
//        title = post.title,
//        body = post.body,
//        user_id = post.user_id
//    )
//}