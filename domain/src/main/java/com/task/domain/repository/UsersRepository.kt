package com.task.domain.repository

import com.task.domain.model.user.User
import com.task.domain.model.wrapper.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface UsersRepository {

    val usersResponse: MutableStateFlow<ApiResponse<List<User>>?>

    fun fetchUsers()

}