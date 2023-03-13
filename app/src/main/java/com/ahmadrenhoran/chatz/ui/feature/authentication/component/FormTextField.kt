package com.ahmadrenhoran.chatz.ui.feature.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme

@Composable
fun FormTextField(
    textValue: String,
    label: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    visualTransformation: VisualTransformation
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(hint) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
    )

}

@Preview(showBackground = true)
@Composable
fun FormTextFieldPreview() {

    ChatzTheme {
        FormTextField(
            textValue = "",
            label = "Email",
            hint = "Enter Email",
            modifier = Modifier,
            leadingIcon = { Icon(Icons.Outlined.AlternateEmail, contentDescription = "Email") },
            trailingIcon = { },

            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(
                onNext = {

                }
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = {}
        )
    }
}