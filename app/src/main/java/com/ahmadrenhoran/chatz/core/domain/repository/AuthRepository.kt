package com.ahmadrenhoran.chatz.core.domain.repository

import com.ahmadrenhoran.chatz.core.domain.model.Response
import com.ahmadrenhoran.chatz.core.domain.model.User

typealias SignInWithEmailResponse = Response<Boolean>
typealias SignUpWithEmailResponse = Response<Boolean>

interface AuthRepository {

    suspend fun firebaseSignInWithEmail(email: String, password: String): SignInWithEmailResponse

    suspend fun firebaseSignUpWithEmail(name: String, email: String, password: String): SignUpWithEmailResponse

}