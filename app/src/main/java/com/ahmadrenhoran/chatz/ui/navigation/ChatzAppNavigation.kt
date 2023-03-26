package com.ahmadrenhoran.chatz.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmadrenhoran.chatz.rememberChatzAppState
import com.ahmadrenhoran.chatz.ui.component.ChatzBottomNavigation
import com.ahmadrenhoran.chatz.ui.feature.authentication.LoginScreen
import com.ahmadrenhoran.chatz.ui.feature.authentication.RegisterScreen
import com.ahmadrenhoran.chatz.ui.feature.authentication.SplashScreen
import com.ahmadrenhoran.chatz.ui.feature.chat.ChatScreen
import com.ahmadrenhoran.chatz.ui.feature.profile.ProfileScreen
import com.ahmadrenhoran.chatz.ui.feature.setting.SettingScreen
import com.ahmadrenhoran.chatz.ui.util.ChatHomeScreen
import com.ahmadrenhoran.chatz.ui.util.ChatScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun ChatzAppNavigation() {
    val appState = rememberChatzAppState()
    val items = listOf(
        ChatHomeScreen.Profile,
        ChatHomeScreen.Chats,
        ChatHomeScreen.Setting
    )

    Scaffold(
        bottomBar = {
            if(appState.shouldShowBottomBar) {
                ChatzBottomNavigation(navController = appState.navController, items = items)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = appState.navController,
            startDestination = ChatScreen.Splash.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ChatScreen.Splash.name) {

                SplashScreen(onAnimationEnd = {
                    val route = if (Firebase.auth.currentUser == null) ChatScreen.Login.name else ChatHomeScreen.Chats.name
                    appState.navController.navigate(route = route) {
                        popUpTo(ChatScreen.Splash.name) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(route = ChatScreen.Login.name) {
                LoginScreen(
                    onClickableTextRegister = { appState.navController.navigate(route = ChatScreen.Register.name) }
                ) { appState.navController.navigate(route = ChatHomeScreen.Chats.name) }
            }

            composable(route = ChatScreen.Register.name) {
                RegisterScreen(
                    onClickableTextLogin = { appState.navController.navigateUp() },
                    onSuccessSignUp = { appState.navController.navigateUp() }
                )
            }

            composable(route = ChatHomeScreen.Profile.name) {
                ProfileScreen()
            }

            composable(route = ChatHomeScreen.Chats.name) {
                ChatScreen()
            }

            composable(route = ChatHomeScreen.Setting.name) {
                SettingScreen()
            }

        }
    }

}