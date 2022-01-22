package com.task.android.di

import com.task.data.remote.WebService
import com.task.data.repository.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProvideModule {

    @Provides
    fun provideWebService() = WebService.invoke()

    @Provides
    fun provideUsersRepositoryImpl(webService: WebService) = UsersRepositoryImpl(webService)


}