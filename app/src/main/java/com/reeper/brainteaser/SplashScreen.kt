package com.reeper.brainteaser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reeper.brainteaser.ui.theme.AppColors
import com.reeper.brainteaser.ui.theme.BrainTeaserTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Use LaunchedEffect to perform an action after a delay when the composable enters the composition.
    LaunchedEffect(key1 = true) {
        // Delay for a few seconds (e.g., 3000 milliseconds = 3 seconds)
        delay(6000L)
        // After the delay, invoke the onTimeout lambda to navigate away
        onTimeout()
    }

    // Box to hold the background and foreground content, filling the entire screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            // Apply a vertical gradient background using colors from AppColors
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppColors.GradientStart, // Start color (lighter orange/yellow)
                        AppColors.GradientEnd    // End color (deeper orange)
                    )
                )
            ),
        contentAlignment = Alignment.Center // Center the content within the Box
    ) {
        // Column to arrange the logo and tagline vertically
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally within the column
            verticalArrangement = Arrangement.Center // Center items vertically within the column (though Box alignment handles primary centering)
        ) {
            // App Logo for the Splash Screen
            // NOTE: Assuming you have an image asset named 'splash_logo' in your drawable resources
            // This should be the logo shown in your attached image.
            Image(
                painter = painterResource(id = R.drawable.splash_logo), // Use your splash screen logo resource
                contentDescription = "Brain Teaser Logo", // Content description for accessibility
                modifier = Modifier.size(300.dp) // Adjust size as needed
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    BrainTeaserTheme {
        // In the preview, the onTimeout lambda does nothing
        SplashScreen(onTimeout = {  })
    }
}