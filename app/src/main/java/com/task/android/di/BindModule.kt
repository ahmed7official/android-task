package com.task.android.di

import com.task.data.repository.UsersRepositoryImpl
import com.task.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository

}