package com.ahmadrenhoran.chatz.ui.feature.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme
import com.ahmadrenhoran.chatz.ui.util.ChatScreen

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatzTheme {
                AuthenticationScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // TODO: check if user already log in or not
    }
}

@Composable
fun AuthenticationScreen() {
    Scaffold(
    ) { innerPadding ->
        val navController: NavHostController = rememberNavController()
        val viewModel: AuthViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = ChatScreen.Splash.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ChatScreen.Splash.name) {
                SplashScreen(onAnimationEnd = { navController.navigate(route = ChatScreen.Login.name) })
            }

            composable(route = ChatScreen.Login.name) {
                LoginScreen(authUiState = uiState,
                    onEmailValueChange = { viewModel.setEmail(it) },
                    onPasswordValueChange = { viewModel.setPassword(it) },
                    onButtonPasswordVisibility = { viewModel.setPasswordVisibility(!viewModel.uiState.value.passwordVisibility) }
                )
            }

            composable(route = ChatScreen.Register.name) {

            }

        }
    }

}

