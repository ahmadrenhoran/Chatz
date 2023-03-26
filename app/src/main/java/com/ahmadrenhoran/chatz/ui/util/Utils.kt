package com.ahmadrenhoran.chatz.ui.util

import java.util.regex.Pattern

object Utils {

    fun isEmailValid(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean {
        if (password.length < 8) return false
        // minimal ada satu huruf besar
        var pattern = Pattern.compile("(?=.*[A-Z])")
        if (!pattern.matcher(password).find()) return false

        // minimal ada satu angka
        pattern = Pattern.compile("(?=.*[0-9])")
        if (!pattern.matcher(password).find()) return false

        return true
    }
}