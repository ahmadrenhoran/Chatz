package com.ahmadrenhoran.chatz.core.domain.usecase.auth

import com.ahmadrenhoran.chatz.core.domain.model.User
import com.ahmadrenhoran.chatz.core.domain.repository.AuthRepository

class SignInWithEmail(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User)  = repository.firebaseSignInWithEmail(user)

}