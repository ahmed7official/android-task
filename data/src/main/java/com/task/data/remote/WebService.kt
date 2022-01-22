package com.task.data.remote


import com.task.data.BuildConfig
import com.task.data.model.user.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface WebService {


    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    companion object {

        operator fun invoke(): WebService {

            val okHttpClint = OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()

            return Retrofit.Builder()
                .client(okHttpClint)
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }

}