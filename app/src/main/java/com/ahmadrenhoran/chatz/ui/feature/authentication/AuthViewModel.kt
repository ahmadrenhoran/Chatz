package com.ahmadrenhoran.chatz.ui.feature.authentication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginRegisterUiState())
    val uiState: StateFlow<LoginRegisterUiState> = _uiState.asStateFlow()


    fun setName(name: String) {
        val loginRegisterUiState = _uiState.value
        updateUiState(
            LoginRegisterUiState(
                name = name,
                email = loginRegisterUiState.email,
                passwordVisibility = loginRegisterUiState.passwordVisibility,
                password = loginRegisterUiState.password,
            )
        )
    }
    fun setEmail(email: String) {
        val loginRegisterUiState = _uiState.value
        updateUiState(
            LoginRegisterUiState(
                name = loginRegisterUiState.name,
                email = email,
                passwordVisibility = loginRegisterUiState.passwordVisibility,
                password = loginRegisterUiState.password,
            )
        )
    }

    fun setPassword(password: String) {
        val loginRegisterUiState = _uiState.value
        updateUiState(
            LoginRegisterUiState(
                name = loginRegisterUiState.name,
                email = loginRegisterUiState.email,
                passwordVisibility = loginRegisterUiState.passwordVisibility,
                password = password,
            )
        )
    }

    fun setPasswordVisibility(passwordVisibility: Boolean) {
        val loginRegisterUiState = _uiState.value
        updateUiState(
            LoginRegisterUiState(
                name = loginRegisterUiState.name,
                email = loginRegisterUiState.email,
                passwordVisibility = passwordVisibility,
                password = loginRegisterUiState.password,
            )
        )
    }

    private fun updateUiState(loginRegisterUiState: LoginRegisterUiState) {
        _uiState.update {
            loginRegisterUiState
        }
    }
}