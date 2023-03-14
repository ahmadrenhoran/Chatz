package com.ahmadrenhoran.chatz.ui.feature.authentication

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadrenhoran.chatz.R
import com.ahmadrenhoran.chatz.ui.theme.ChatzTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(onAnimationEnd: () -> Unit) {
    // Buat Animatable untuk mengatur opacity dari splash screen
    val opacity = remember { Animatable(0f) }

    // Animasikan opacity dari 0 menjadi 1
    LaunchedEffect(Unit) {
        opacity.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
        // Setelah animasi selesai, jalankan fungsi onAnimationEnd
        onAnimationEnd()
    }
    val isDarkTheme = isSystemInDarkTheme()
    //tes je
    val lottieLogo =
        if (isDarkTheme) LottieCompositionSpec.RawRes(R.raw.chat_green_light)
        else LottieCompositionSpec.RawRes(R.raw.final_logo)
    val composition by rememberLottieComposition(lottieLogo)


    // Tampilkan konten splash screen
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition, modifier = Modifier
                .width(240.dp)
                .height(240.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 36.sp,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.alpha(opacity.value)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ChatzTheme {
        SplashScreen {

        }
    }
}

