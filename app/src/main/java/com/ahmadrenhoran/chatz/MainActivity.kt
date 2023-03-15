package com.ahmadrenhoran.chatz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmadrenhoran.chatz.ui.feature.authentication.*
import com.ahmadrenhoran.chatz.ui.navigation.AuthNavigation
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme
import com.ahmadrenhoran.chatz.ui.util.ChatScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ChatzTheme {
                // A surface container using the 'background' color from the theme
                setContent {
                    ChatzTheme {
                        AuthNavigation()
                    }
                }
            }
        }
    }
}





