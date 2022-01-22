package com.task.data.repository

import com.task.data.map
import com.task.data.remote.WebService
import com.task.data.remote.apiRequest
import com.task.domain.model.user.User
import com.task.domain.model.wrapper.ApiResponse
import com.task.domain.repository.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersRepositoryImpl(
    private val webService: WebService,
    private val externalDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {

    override val usersResponse: MutableStateFlow<ApiResponse<List<User>>?> = MutableStateFlow(null)

    override fun fetchUsers() {
        CoroutineScope(externalDispatcher).launch {

            usersResponse.emit(
                ApiResponse(
                    statusMessage = ApiResponse.LOADING,
                    data = null,
                    statusCode = 0,
                    success = false
                )
            )

            val response = apiRequest { webService.getUsers() }
            val mappedResponse = ApiResponse(
                success = response.success,
                statusCode = response.statusCode,
                statusMessage = response.statusMessage,
                data = response.data?.map { it.map() }
            )

            usersResponse.emit(mappedResponse)

        }
    }


}