package com.reeper.brainteaser

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reeper.brainteaser.ui.theme.*


@Composable
fun ScoreScreen(
    score: Int, // Score is now required, defaults removed as it's passed from navigation
    total: Int, // Total is now required, defaults removed
    userName: String = "Player", // userName can still have a default or be passed
    onExit: () -> Unit, // Callbacks are required
    onHome: () -> Unit // Callbacks are required
) {
    // Determine if the user passed (example threshold: >= 60%)
    val passed = score.toFloat() / total.toFloat() >= 0.6f // Example pass criteria
    // Determine accent color based on pass/fail status
    val accentColor = if (passed) AppColors.Correct else AppColors.Incorrect
    // Determine emoji and message based on pass/fail status
    val statusEmoji = if (passed) "😊" else "😔"
    val statusMessage = if (passed) "You have passed" else "Better luck next time!" // Updated message

    // Main column layout for the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Using a direct color for background
            .padding(24.dp)
    ) {
        // Reusable Header composable
        AppHeader(userName = userName)

        Spacer(modifier = Modifier.height(32.dp))

        // Score Circle display
        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.CenterHorizontally) // Center the circle horizontally
                .background(accentColor, shape = CircleShape), // Dynamic background color
            contentAlignment = Alignment.Center // Center content inside the circle
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Score text (e.g., "4/5")
                Text(
                    text = "$score/$total",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                // "your score" label
                Text(
                    text = "your score",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Progress Indicator showing score percentage
        LinearProgressIndicator(
            progress = score.toFloat() / total.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = accentColor, // Dynamic color
            trackColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Passed/Fail Message Box with border
        Text(
            text = "$statusEmoji $statusMessage",
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, accentColor, RoundedCornerShape(12.dp)) // Dynamic border color
                .padding(vertical = 14.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = accentColor, // Dynamic text color
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Exit Button (Navigates to Home in this implementation)
        Button( // Changed to Button for consistent styling, could be OutlinedButton
            onClick = onExit, // Call the onExit lambda
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.ExitButton) // Using color from centralized constants
        ) {
            Text(
                text = "Exit →",
                fontSize = 24.sp,
                color = Color.White // Ensure text color is readable on button background
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Congratulations Box (only if passed)
        if (passed) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.CongratulationBackground, shape = RoundedCornerShape(12.dp)) // Using color from centralized constants
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "😊 Congratulations",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }

        // Prompt and Home Button section
        // Using weight(1f) to push this section down, accommodating the optional Congratulations Box
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(), // Take remaining space
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom // Align content to the bottom
        ) {
            Text(
                text = "Want to try more questions?",
                style = TextStyle( // Using TextStyle directly
                    fontSize = 20.sp, // Slightly reduced font size
                    fontWeight = FontWeight.Bold,
                    color = AppColors.PrimaryVariant // Using color from centralized constants
                ),
                textAlign = TextAlign.Center // Center align text
            )

            Text(
                text = "Click below.....",
                style = TextStyle( // Using TextStyle directly
                    fontSize = 20.sp, // Slightly reduced font size
                    fontWeight = FontWeight.Bold,
                    color = AppColors.PrimaryVariant, // Using color from centralized constants
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(24.dp)) // Reduced space before button

            // Home Button (Navigates back to Start)
            Button(
                onClick = onHome, // Call the onHome lambda
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.HomeButton), // Using color from centralized constants
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Home →",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScoreScreenPreview() {
    BrainTeaserTheme {

        ScoreScreen(
            score = 4,
            total = 5,
            userName = "PreviewUser",
            onExit = {  },
            onHome = {  }
        )
    }
}
