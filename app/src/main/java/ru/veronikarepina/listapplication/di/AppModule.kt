package ru.veronikarepina.listapplication.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.veronikarepina.listapplication.screens.PostsViewModel

val appModule = module {

    viewModel {
        PostsViewModel(
            getPagerRepository = get(),
            postDao = get()
        )
    }
}