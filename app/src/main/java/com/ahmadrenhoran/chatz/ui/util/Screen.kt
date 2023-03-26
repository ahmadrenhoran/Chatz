package com.ahmadrenhoran.chatz.ui.util

import android.icu.text.CaseMap.Title
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.ahmadrenhoran.chatz.R

enum class ChatScreen(@StringRes val route: Int) {
    Login(route = R.string.login),
    Register(route = R.string.register),
    Splash(route = R.string.splash)
}

enum class ChatHomeScreen(@StringRes val title: Int, val route: String, val icon: ImageVector) {
    Profile(title = R.string.profile, "Profile", Icons.Filled.Person),
    Chats(title = R.string.chats, "Chats", Icons.Filled.Chat),
    Setting(title = R.string.Setting, "Setting", Icons.Filled.Settings),
}