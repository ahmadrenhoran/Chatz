package com.ahmadrenhoran.chatz.core.domain.repository

import com.ahmadrenhoran.chatz.core.domain.model.Response
import com.ahmadrenhoran.chatz.core.domain.model.User

typealias SignInWithEmailResponse = Response<Boolean>

interface AuthRepository {

    suspend fun firebaseSignInWithEmail(user: User): SignInWithEmailResponse

    suspend fun firebaseSignUpWithEmail(user: User): SignInWithEmailResponse

}