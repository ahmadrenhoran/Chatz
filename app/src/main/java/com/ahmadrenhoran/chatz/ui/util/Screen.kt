package com.ahmadrenhoran.chatz.ui.util

import androidx.annotation.StringRes
import com.ahmadrenhoran.chatz.R

enum class ChatScreen(@StringRes val route: Int) {
    Login(route = R.string.login),
    Register(route = R.string.register),
    Splash(route = R.string.splash)
}