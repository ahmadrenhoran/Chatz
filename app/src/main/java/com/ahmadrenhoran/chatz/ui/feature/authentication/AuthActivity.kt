package com.ahmadrenhoran.chatz.ui.feature.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme

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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

    }

}

