package com.ahmadrenhoran.chatz.ui.feature.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.chatz.core.domain.model.Response
import com.ahmadrenhoran.chatz.core.domain.model.User
import com.ahmadrenhoran.chatz.core.domain.repository.SignInWithEmailResponse
import com.ahmadrenhoran.chatz.core.domain.usecase.auth.AuthUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCases: AuthUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginRegisterUiState())
    val uiState: StateFlow<LoginRegisterUiState> = _uiState.asStateFlow()

    private val _emailResponse = MutableStateFlow<SignInWithEmailResponse>(Response.Success(false))
    val emailResponse: StateFlow<SignInWithEmailResponse> = _emailResponse.asStateFlow()

    fun signInWithEmail() = viewModelScope.launch {
        val user = User("1", "2", uiState.value.email, uiState.value.password, "3")
        Log.d("AuthViewModelLogging", "signInWithEmail: $user")
        _emailResponse.value = Response.Loading
        _emailResponse.value = authUseCases.signInWithEmail.invoke(user)
    }

    fun signUpWithEmail() = viewModelScope.launch {
        val user = User("1", "2", uiState.value.email, uiState.value.password, "3")
        Log.d("AuthViewModelLogging", "signInWithEmail: $user")
        _emailResponse.value = Response.Loading
        _emailResponse.value = authUseCases.signUpWithEmail.invoke(user)
    }

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