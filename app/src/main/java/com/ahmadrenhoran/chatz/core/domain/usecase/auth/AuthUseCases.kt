package com.ahmadrenhoran.chatz.core.domain.usecase.auth

data class AuthUseCases(
    val signInWithEmail: SignInWithEmail,
    val signUpWithEmail: SignUpWithEmail
)