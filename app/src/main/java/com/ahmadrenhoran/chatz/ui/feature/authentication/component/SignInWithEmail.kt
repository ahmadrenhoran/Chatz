package com.ahmadrenhoran.chatz.ui.feature.authentication.component

import androidx.compose.runtime.*
import com.ahmadrenhoran.chatz.core.domain.model.Response
import com.ahmadrenhoran.chatz.ui.component.ProgressBar
import com.ahmadrenhoran.chatz.ui.feature.authentication.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignInWithEmail(
    viewModel: AuthViewModel = getViewModel(),
    onSuccessSignIn: (signIn: Boolean) -> Unit
) {
    when (val signInResponse = viewModel.emailResponseSignIn) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInResponse.data?.let { signIn ->
            LaunchedEffect(signIn) {
                onSuccessSignIn(signIn)
            }
        }
        is Response.Error -> {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            if (errorDialogPopupShown) {
                Dialog(
                    text = signInResponse.e.localizedMessage ?: ""
                ) { errorDialogPopupShown = false }
            }
        }
    }
}