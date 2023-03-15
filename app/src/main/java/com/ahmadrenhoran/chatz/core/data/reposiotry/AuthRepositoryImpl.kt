package com.ahmadrenhoran.chatz.core.data.reposiotry

import android.util.Log
import com.ahmadrenhoran.chatz.core.domain.model.Response
import com.ahmadrenhoran.chatz.core.domain.model.User
import com.ahmadrenhoran.chatz.core.domain.repository.AuthRepository
import com.ahmadrenhoran.chatz.core.domain.repository.SignInWithEmailResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl (private val auth: FirebaseAuth): AuthRepository {
    override suspend fun firebaseSignInWithEmail(
        user: User
    ): SignInWithEmailResponse {
        return try {
            auth.signInWithEmailAndPassword(user.email, user.password).await()
            Log.d(TAG, "firebaseSignInWithEmail: berhasil")
            Response.Success(true)
        } catch (e: Exception) {
            Log.d(TAG, "firebaseSignInWithEmail: $e")
            Response.Error(e)
        }
    }

    override suspend fun firebaseSignUpWithEmail(
        user: User
    ): SignInWithEmailResponse {
        return try {
            auth.createUserWithEmailAndPassword(user.email, user.password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    companion object {
        const val TAG = "AuthRepositoryImplLog"
    }
}