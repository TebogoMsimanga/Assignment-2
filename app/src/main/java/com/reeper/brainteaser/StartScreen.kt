package com.reeper.brainteaser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reeper.brainteaser.ui.theme.*


@Composable
fun StartScreen(onStartClick: () -> Unit) {
    // Use a Box to fill the screen and set padding and background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Using a direct color for background
            .padding(16.dp)
    ) {
        // Main column to hold all content vertically
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Reusable Header composable for consistency
            AppHeader(userName = "User") // Hardcoded User for now

            Spacer(modifier = Modifier.height(16.dp)) // Add some space below the header

            // Using weight(1f) to push content to center vertically within the column
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.brain_teaser_logo), // Assuming R.drawable.brain_teaser_logo exists
                    contentDescription = "Brain Teaser Logo",
                    modifier = Modifier.size(240.dp) // Use size for both width and height
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Question Icon
                Image(
                    painter = painterResource(id = R.drawable.assignment), // Assuming R.drawable.assignment exists
                    contentDescription = "Question Icon",
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Prompt Text 1
                Text(
                    text = "Are you ready?",
                    style = TextStyle( // Using TextStyle directly, could use Typography from theme
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.PrimaryVariant // Using color from centralized constants
                    )
                )

                // Prompt Text 2
                Text(
                    text = "Click the button below\nto begin",
                    style = TextStyle( // Using TextStyle directly
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.PrimaryVariant, // Using color from centralized constants
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(96.dp))

                // Start Button
                Button(
                    onClick = onStartClick, // Call the lambda when clicked
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary), // Using color from centralized constants
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = "Start →",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    BrainTeaserTheme {
        StartScreen(onStartClick = { /* Do nothing in preview */ })
    }
}