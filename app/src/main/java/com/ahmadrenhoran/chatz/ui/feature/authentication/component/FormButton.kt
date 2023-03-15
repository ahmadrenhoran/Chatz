package com.ahmadrenhoran.chatz.ui.feature.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme

@Composable
fun FormButton(modifier: Modifier, buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = MaterialTheme.shapes.medium,
        content = {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.displaySmall
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FormButtonPreview() {

    ChatzTheme {

    }
}