package com.ahmadrenhoran.chatz.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
    visualTransformation: VisualTransformation,
    isError: Boolean = false,
    supportingText: String = ""
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = onValueChange,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = supportingText)
            }
        },
        label = { Text(label, style = MaterialTheme.typography.bodyLarge) },
        placeholder = { Text(hint, style = MaterialTheme.typography.bodyLarge) },
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