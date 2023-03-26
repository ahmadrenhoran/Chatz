package com.ahmadrenhoran.chatz.ui.feature.authentication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.chatz.core.domain.model.Response
import com.ahmadrenhoran.chatz.core.domain.model.User
import com.ahmadrenhoran.chatz.core.domain.repository.SignInWithEmailResponse
import com.ahmadrenhoran.chatz.core.domain.repository.SignUpWithEmailResponse
import com.ahmadrenhoran.chatz.core.domain.usecase.auth.AuthUseCases
import com.ahmadrenhoran.chatz.ui.util.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCases: AuthUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginRegisterUiState())
    val uiState: StateFlow<LoginRegisterUiState> = _uiState.asStateFlow()

    var emailResponseSignIn by mutableStateOf<SignInWithEmailResponse>(Response.Success(false))
        private set

    var emailResponseSignUp by mutableStateOf<SignUpWithEmailResponse>(Response.Success(false))
        private set

    var isFormValid by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        isFormValid = false
        isLoading = false
    }

    fun signInWithEmail() = viewModelScope.launch {
        isLoading = true
        emailResponseSignIn = Response.Loading
        emailResponseSignIn = authUseCases.signInWithEmail.invoke(
            _uiState.value.email, _uiState.value.password
        )
        isLoading = false
    }

    fun signUpWithEmail() = viewModelScope.launch {
        isLoading = true
        emailResponseSignUp = Response.Loading
        emailResponseSignUp = authUseCases.signUpWithEmail.invoke(
            _uiState.value.name,
            _uiState.value.email,
            _uiState.value.password
        )
        isLoading = false
    }

    fun validatingForm() {
        _uiState.value.apply {
            if (Utils.isEmailValid(email.trim()) && Utils.isPasswordValid(password.trim())) {
                isFormValid = true
                return
            }
            isFormValid = false
        }
    }

    fun setName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun setEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun setPassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun setPasswordVisibility(passwordVisibility: Boolean) {
        _uiState.update {
            it.copy(passwordVisibility = passwordVisibility)
        }
    }


}