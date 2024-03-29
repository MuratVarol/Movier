package com.varol.movier.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import com.varol.movier.BASE_LINK
import com.varol.movier.remote.Api
import com.varol.movier.util.network.CacheInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


private const val CLIENT_TIME_OUT = 120L
private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
private const val CLIENT_CACHE_DIRECTORY = "http"


val networkModule = module {
    single { createCache(androidContext()) }
    single { createChuckInterceptor(androidContext()) }
    single { createCacheInterceptor() }
    single(name = "baseUrl") { getBaseUrl() }
    single { createOkHttpClient(get(), get(), get()) }
    single { createGson() }
    single { createWebService<Api>(get(), get(), get(name = "baseUrl")) }

}


/**
 * Create Cache object for OkHttp instance
 */
fun createCache(context: Context): Cache = Cache(
    File(
        context.cacheDir,
        CLIENT_CACHE_DIRECTORY
    ),
    CLIENT_CACHE_SIZE
)

/**
 * returns Gson parser
 */
fun createGson(): Gson {
    return Gson()
}

/**
 * returns ChuckInterceptor
 * ChuckInterceptor: API request-response interceptor for debugging API traffic
 */
fun createChuckInterceptor(context: Context): ChuckInterceptor {
    return ChuckInterceptor(context)
}

/**
 * returns CacheInterceptor
 */
fun createCacheInterceptor(): CacheInterceptor {
    return CacheInterceptor()
}

/**
 * Create OkHttp client with required interceptors and defined timeouts
 */
fun createOkHttpClient(
    cache: Cache,
    chuckInterceptor: ChuckInterceptor,
    cacheInterceptor: CacheInterceptor
): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
    okHttpBuilder
        .addInterceptor(chuckInterceptor)
        .addInterceptor(cacheInterceptor)
        .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .cache(cache)
    return okHttpBuilder.build()
}

/**
 * returns BASE_LINK for API
 */
fun getBaseUrl(): String {
    return BASE_LINK
}


/**
 * Create Retrofit Client
 *
 * <reified T> private func let us using reflection.
 * We can use generics and reflection so ->
 *  we don't have to define required Api Interface here
 */
inline fun <reified T> createWebService(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}
