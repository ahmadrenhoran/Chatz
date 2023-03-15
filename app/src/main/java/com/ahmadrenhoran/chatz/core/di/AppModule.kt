package com.ahmadrenhoran.chatz.core.di


import com.ahmadrenhoran.chatz.core.data.reposiotry.AuthRepositoryImpl
import com.ahmadrenhoran.chatz.core.domain.repository.AuthRepository
import com.ahmadrenhoran.chatz.core.domain.usecase.auth.AuthUseCases
import com.ahmadrenhoran.chatz.core.domain.usecase.auth.SignInWithEmail
import com.ahmadrenhoran.chatz.core.domain.usecase.auth.SignUpWithEmail
import com.ahmadrenhoran.chatz.ui.feature.authentication.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val AppModule = module {
    single {
        FirebaseAuth.getInstance()
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    viewModel {
        AuthViewModel(get())
    }

    single {
        AuthUseCases(SignInWithEmail(get()), SignUpWithEmail(get()))
    }
}