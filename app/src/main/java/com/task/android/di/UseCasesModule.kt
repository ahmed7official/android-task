package com.task.android.di

import com.task.domain.repository.UsersRepository
import com.task.domain.use_case.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetUsersUseCase(usersRepository: UsersRepository) =
        GetUsersUseCase(usersRepository)


}