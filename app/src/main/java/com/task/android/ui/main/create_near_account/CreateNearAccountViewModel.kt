package com.task.android.ui.main.create_near_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNearAccountViewModel @Inject constructor() : ViewModel() {

    val fullName = MutableStateFlow("")
    val walletId = MutableStateFlow("")
    val enableRegistration = MutableStateFlow(false)

    fun shouldEnableRegistration(): Boolean {
        return fullName.value.isNotBlank() && walletId.value.isNotBlank()
    }

    init {

        viewModelScope.launch {
            fullName.collect {
                enableRegistration.value = shouldEnableRegistration()
            }
        }

        viewModelScope.launch {
            walletId.collect {
                enableRegistration.value = shouldEnableRegistration()
            }
        }

    }

}