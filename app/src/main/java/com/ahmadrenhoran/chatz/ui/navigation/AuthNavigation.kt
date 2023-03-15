package com.ahmadrenhoran.chatz.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmadrenhoran.chatz.ui.feature.authentication.AuthViewModel
import com.ahmadrenhoran.chatz.ui.feature.authentication.LoginScreen
import com.ahmadrenhoran.chatz.ui.feature.authentication.RegisterScreen
import com.ahmadrenhoran.chatz.ui.feature.authentication.SplashScreen
import com.ahmadrenhoran.chatz.ui.util.ChatScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthNavigation() {
    Scaffold { innerPadding ->
        val navController: NavHostController = rememberNavController()
        val viewModel: AuthViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ChatScreen.Splash.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ChatScreen.Splash.name) {

                SplashScreen(onAnimationEnd = {
                    navController.navigate(route = ChatScreen.Login.name) {
                        popUpTo(ChatScreen.Splash.name) {
                            inclusive = true
                        }
                    }
                })

            }

            composable(route = ChatScreen.Login.name) {
                LoginScreen(authUiState = uiState,
                    onEmailValueChange = { viewModel.setEmail(it) },
                    onPasswordValueChange = { viewModel.setPassword(it) },
                    onButtonPasswordVisibility = { viewModel.setPasswordVisibility(!viewModel.uiState.value.passwordVisibility) },
                    onLoginButton = { viewModel.signInWithEmail() },
                    onClickableTextRegister = { navController.navigate(route = ChatScreen.Register.name) }
                )
            }

            composable(route = ChatScreen.Register.name) {
                RegisterScreen(
                    authUiState = uiState,
                    onNameChange = { viewModel.setName(it) },
                    onEmailValueChange = { viewModel.setEmail(it) },
                    onPasswordValueChange = { viewModel.setPassword(it) },
                    onButtonPasswordVisibility = { viewModel.setPasswordVisibility(!viewModel.uiState.value.passwordVisibility) },
                    onRegisterButton = {viewModel.signUpWithEmail() },
                    onClickableTextLogin = { navController.navigateUp() }
                )
            }

        }
    }

}