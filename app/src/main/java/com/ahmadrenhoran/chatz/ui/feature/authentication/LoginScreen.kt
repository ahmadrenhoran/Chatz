package com.ahmadrenhoran.chatz.ui.feature.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.chatz.R
import com.ahmadrenhoran.chatz.ui.component.FormButton
import com.ahmadrenhoran.chatz.ui.component.FormTextField
import com.ahmadrenhoran.chatz.ui.component.FormTextNav
import com.ahmadrenhoran.chatz.ui.component.LottieAnimationCompose
import com.ahmadrenhoran.chatz.ui.feature.authentication.component.SignInWithEmail
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme
import com.airbnb.lottie.compose.*
import org.koin.androidx.compose.getViewModel


@Composable
fun LoginScreen(
    onClickableTextRegister: (Int) -> Unit,
    viewModel: AuthViewModel = getViewModel(),
    onSuccessSignIn: () -> Unit
) {
    val authUiState = viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    if (!viewModel.isLoading) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimationCompose(modifier = Modifier
                .width(260.dp)
                .height(260.dp), resId = R.raw.social_interaction)
            Text(
                text = stringResource(R.string.login),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            FormTextField(
                textValue = authUiState.value.email,
                onValueChange = { viewModel.setEmail(it) },
                label = stringResource(R.string.email),
                hint = stringResource(R.string.enter_email),
                modifier = Modifier,
                leadingIcon = {
                    Icon(
                        Icons.Outlined.AlternateEmail,
                        contentDescription = stringResource(R.string.email),
                    )
                },
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
                textValue = authUiState.value.password,
                onValueChange = {
                    viewModel.setPassword(it)
                },
                label = stringResource(R.string.password),
                hint = stringResource(R.string.enter_password),
                modifier = Modifier,
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Lock,
                        contentDescription = stringResource(R.string.password)
                    )
                },
                trailingIcon = {
                    val passwordVisibility = authUiState.value.passwordVisibility
                    IconButton(onClick = { viewModel.setPasswordVisibility(!viewModel.uiState.value.passwordVisibility) }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (passwordVisibility) stringResource(R.string.hide_password) else stringResource(
                                R.string.show_password
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (authUiState.value.email.isNotEmpty() && authUiState.value.password.isNotEmpty()) {
                            viewModel.signInWithEmail()
                        }
                    }
                ),
                visualTransformation = if (authUiState.value.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(24.dp))
            FormButton(modifier = Modifier, stringResource(id = R.string.login)) {
                if (authUiState.value.email.isNotEmpty() && authUiState.value.password.isNotEmpty()) {
                    viewModel.signInWithEmail()
                }
            }
            FormTextNav(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.Bottom),
                firstText = "I don't have an account? ",
                secondText = "Register",
                onClick = onClickableTextRegister
            )

        }
    }
    SignInWithEmail(onSuccessSignIn = { signIn ->
        if (signIn) {
            onSuccessSignIn()
        }
    })
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ChatzTheme {
        LoginScreen({}) { }
    }
}