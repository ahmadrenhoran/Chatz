package com.ahmadrenhoran.chatz.ui.feature.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.chatz.ui.feature.authentication.component.FormTextField
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme


@Composable
fun LoginScreen(
    authUiState: LoginRegisterUiState,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onButtonPasswordVisibility: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.padding(24.dp)) {
        FormTextField(
            textValue = authUiState.email,
            onValueChange = onEmailValueChange,
            label = "Email",
            hint = "Enter Email",
            modifier = Modifier,
            leadingIcon = { Icon(Icons.Outlined.AlternateEmail, contentDescription = "Email") },
            trailingIcon = { },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            visualTransformation = VisualTransformation.None
        )

        FormTextField(
            textValue = authUiState.password,
            onValueChange = onPasswordValueChange,
            label = "Password",
            hint = "Enter Password",
            modifier = Modifier,
            leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Password") },
            trailingIcon = {
                val passwordVisibility = authUiState.passwordVisibility
                IconButton(onClick = onButtonPasswordVisibility) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            visualTransformation = if (authUiState.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ChatzTheme {
        LoginScreen(LoginRegisterUiState(), {}, {}, {})
    }
}