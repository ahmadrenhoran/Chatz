package com.ahmadrenhoran.chatz.ui.util

import androidx.annotation.StringRes
import com.ahmadrenhoran.chatz.R

enum class ChatScreen(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.register)
}