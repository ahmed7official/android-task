package com.task.android.ui.main.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val email = MutableStateFlow("")
    val phone = MutableStateFlow("")

    val enableRegistration = MutableStateFlow(false)


    val wallet = MutableStateFlow("")

    fun shouldEnableRegistration(): Boolean {
        return email.value.isNotBlank() && phone.value.isNotBlank()
    }

    init {

        viewModelScope.launch {
            email.collect {
                enableRegistration.value = shouldEnableRegistration()
            }
        }

        viewModelScope.launch {
            phone.collect {
                enableRegistration.value = shouldEnableRegistration()
            }
        }

    }

}