package com.rodionov.oktan.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rodionov.oktan.BuildConfig
import com.rodionov.oktan.data.network.api.FuelStationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { buildJson() }
    single { buildOkHttp() }
//    single { buildAuthInterceptor(get()) }
    single { buildRetrofit(get(), get()) }
    single { buildFuelStationsApi(get()) }
}

const val TIMEOUT: Long = 30

private fun buildFuelStationsApi(retrofit: Retrofit) : FuelStationApi {
    return retrofit.create(FuelStationApi::class.java)
}

private fun buildJson() = GsonBuilder().create()

//private fun buildAuthInterceptor(prefs: Prefs): AuthorizationInterceptor {
//    return AuthorizationInterceptor(prefs)
//}

private fun buildOkHttp(/*authorizationInterceptor: AuthorizationInterceptor*/): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    okHttpClientBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    okHttpClientBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS)

//    if (BuildConfig.DEBUG) {
    //Ignore for sending newRequest (because duplicate query)
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    val loggingInterceptor =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
    okHttpClientBuilder.addInterceptor(loggingInterceptor)
//            .addInterceptor(authorizationInterceptor)
//        okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
//    }

    return okHttpClientBuilder.build()
}

private fun buildRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}