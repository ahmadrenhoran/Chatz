package com.ahmadrenhoran.chatz.core.data.reposiotry

import android.util.Log
import com.ahmadrenhoran.chatz.core.domain.model.Response
import com.ahmadrenhoran.chatz.core.domain.model.User
import com.ahmadrenhoran.chatz.core.domain.repository.AuthRepository
import com.ahmadrenhoran.chatz.core.domain.repository.SignInWithEmailResponse
import com.ahmadrenhoran.chatz.core.domain.repository.SignUpWithEmailResponse
import com.ahmadrenhoran.chatz.core.utils.Constants.CREATED_AT
import com.ahmadrenhoran.chatz.core.utils.Constants.DISPLAY_NAME
import com.ahmadrenhoran.chatz.core.utils.Constants.EMAIL
import com.ahmadrenhoran.chatz.core.utils.Constants.PHOTO_URL
import com.ahmadrenhoran.chatz.core.utils.Constants.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val auth: FirebaseAuth, private val db: FirebaseFirestore) :
    AuthRepository {
    override suspend fun firebaseSignInWithEmail(
        email: String,
        password: String
    ): SignInWithEmailResponse {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: FirebaseAuthException) {
            Response.Error(e)
        }
    }

    override suspend fun firebaseSignUpWithEmail(
        name: String,
        email: String,
        password: String
    ): SignUpWithEmailResponse {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }
            auth.currentUser?.updateProfile(profileUpdates)?.await()
            addUserToFirestore()
            Response.Success(true)
        } catch (e: FirebaseAuthException) {
            Response.Error(e)
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }

    companion object {
        const val TAG = "AuthRepositoryImplLog"
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to FieldValue.serverTimestamp()
)