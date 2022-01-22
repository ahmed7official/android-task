package com.task.domain.use_case

import com.task.domain.model.user.User
import com.task.domain.model.wrapper.ApiResponse
import com.task.domain.repository.UsersRepository
import kotlinx.coroutines.flow.StateFlow

class GetUsersUseCase(private val usersRepository: UsersRepository) {

    val usersResponse = usersRepository.usersResponse as StateFlow<ApiResponse<List<User>>?>

    fun fetchUsers() = usersRepository.fetchUsers()

}