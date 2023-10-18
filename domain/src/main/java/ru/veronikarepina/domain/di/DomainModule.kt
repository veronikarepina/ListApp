//package ru.veronikarepina.domain.di
//
//import org.koin.dsl.module
//import ru.veronikarepina.domain.usecase.GetPostsUseCase
//
//val domainModule = module{
//
//    factory {
//        GetPostsUseCase(postsRepository = get())
//    }
//}