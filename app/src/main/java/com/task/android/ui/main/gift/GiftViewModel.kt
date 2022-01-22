package com.task.android.ui.main.gift

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.domain.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GiftViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    val query = MutableStateFlow("")

    val users = getUsersUseCase.usersResponse.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    fun fetchUsers() = getUsersUseCase.fetchUsers()

    init {

        if (users.value == null) {
            fetchUsers()
        }

    }

}