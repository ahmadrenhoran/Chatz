package com.ahmadrenhoran.chatz.ui.feature.authentication.component

import androidx.compose.runtime.*
import com.ahmadrenhoran.chatz.core.domain.model.Response.Loading
import com.ahmadrenhoran.chatz.core.domain.model.Response.Success
import com.ahmadrenhoran.chatz.core.domain.model.Response.Error
import com.ahmadrenhoran.chatz.ui.component.ProgressBar
import com.ahmadrenhoran.chatz.ui.feature.authentication.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignUpWithEmail(
    viewModel: AuthViewModel = getViewModel(),
    onSuccessSignUp: (signUp: Boolean) -> Unit
) {
    when (val signOutResponse = viewModel.emailResponseSignUp) {
        is Loading -> ProgressBar()
        is Success -> signOutResponse.data?.let { signUp ->
            LaunchedEffect(signUp) {
                onSuccessSignUp(signUp)
            }
        }
        is Error -> {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            if (errorDialogPopupShown) {
                Dialog(
                    text = signOutResponse.e.localizedMessage ?: ""
                ) { errorDialogPopupShown = false }
            }
        }
    }
}
