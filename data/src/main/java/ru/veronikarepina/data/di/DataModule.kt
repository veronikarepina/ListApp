package ru.veronikarepina.data.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.veronikarepina.data.BuildConfig
import ru.veronikarepina.data.network.PostApi
import ru.veronikarepina.data.repository.PagerRepositoryImpl
import ru.veronikarepina.data.repository.PostRepositoryImpl
import ru.veronikarepina.data.room.PostDataBase
import ru.veronikarepina.data.utils.Constants
import ru.veronikarepina.domain.repository.GetPagerRepository
import ru.veronikarepina.domain.repository.GetPostsRepository

fun provideDataBase(context: Context) = PostDataBase.getInstance(context)
fun provideDao(dataBase: PostDataBase) = dataBase.postsDao()

const val baseUrl = Constants.BASE_URL
//const val networkTimeout = Constants.NETWORK_TIMEOUT

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideOkHttpClient() = if (BuildConfig.DEBUG){
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val requestInterceptor = Interceptor{ chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }

    OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(requestInterceptor)
        .build()
} else {
    OkHttpClient
        .Builder()
        .build()
}

fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): PostApi =
    Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(PostApi::class.java)


val dataModule = module {

    single { baseUrl }
    //single { networkTimeout }
    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(baseUrl = get(), gson = get(), client = get()) }

    single<GetPostsRepository> {
        PostRepositoryImpl(postApi = get())
    }
    single<GetPagerRepository>{
        PagerRepositoryImpl(postApi = get())
    }

    single {
        provideDataBase(context = get())
    }

    single {
        provideDao(dataBase = get())
    }
}