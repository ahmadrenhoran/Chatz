package com.ahmadrenhoran.chatz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ahmadrenhoran.chatz.ui.navigation.ChatzAppNavigation
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatzTheme {
                // A surface container using the 'background' color from the theme
                setContent {
                    ChatzTheme {
                        ChatzAppNavigation()
                    }
                }
            }
        }
    }
}





