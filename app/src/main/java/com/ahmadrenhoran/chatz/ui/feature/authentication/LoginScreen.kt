package com.ahmadrenhoran.chatz.ui.feature.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.chatz.R
import com.ahmadrenhoran.chatz.ui.feature.authentication.component.FormTextField
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun LoginScreen(
    authUiState: LoginRegisterUiState,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onButtonPasswordVisibility: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.social_interaction))
    Column(
        modifier = Modifier.padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LottieAnimation(
            composition, modifier = Modifier
                .width(260.dp)
                .height(260.dp), iterations = LottieConstants.IterateForever
        )
        Text(
            text = "Login",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
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
        Spacer(modifier = Modifier.height(8.dp))
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