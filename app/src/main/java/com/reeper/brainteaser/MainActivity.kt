package com.reeper.brainteaser

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.reeper.brainteaser.ui.theme.BrainTeaserTheme

// Define the different screens in the application using a sealed class
sealed class Screen {
    // Add a new state for the Splash screen
    object Splash : Screen()

    // Object representing the Start screen
    object Start : Screen()

    // Object representing the Quiz screen
    object Quiz : Screen()

    // Data class representing the Score screen, holding the final score
    data class Score(val finalScore: Int) : Screen()
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display
        enableEdgeToEdge()
        // Set the content of the activity using Jetpack Compose
        setContent {
            // Apply the custom theme for the application
            BrainTeaserTheme {
                // The main composable that manages screen navigation
                AppNavigationHost()
            }
        }
    }
}


@SuppressLint("ContextCastToActivity")
@Composable
fun AppNavigationHost() {
    // Get the current Activity context to be able to finish it
    val activity = LocalContext.current as Activity

    // State variable to keep track of the currently displayed screen
    // Starts with the Splash screen
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

    // Use a when expression to determine which screen composable to display
    // based on the currentScreen state.
    when (currentScreen) {
        // If the current screen is Splash, display SplashScreen.
        // Pass a lambda that updates the currentScreen to Start when the splash timeout occurs.
        is Screen.Splash -> SplashScreen(
            onTimeout = { currentScreen = Screen.Start }
        )
        // If the current screen is Start, display StartScreen.
        // Pass a lambda that updates the currentScreen to Quiz when Start is clicked.
        is Screen.Start -> StartScreen(
            onStartClick = { currentScreen = Screen.Quiz }
        )
        // If the current screen is Quiz, display QuizScreen.
        // Pass a lambda that updates the currentScreen to Score, passing the final score,
        // when the quiz is completed.
        is Screen.Quiz -> QuizScreen(
            onQuizComplete = { finalScore -> currentScreen = Screen.Score(finalScore) }
            // Note: If using NavController in QuizScreen, this won't be called.
            // Ensure consistency in navigation approach (state vs NavController).
        )
        // If the current screen is Score, display ScoreScreen.
        // Extract the finalScore from the Score object.
        // Pass lambdas to handle navigation from the Score screen:
        // - onHome: Navigates back to the Start screen.
        // - onExit: Modified to finish the activity when called.
        is Screen.Score -> ScoreScreen(
            score = (currentScreen as Screen.Score).finalScore,
            total = sampleQuestions.size, // Use the actual total number of questions (assuming sampleQuestions is accessible)
            // userName is hardcoded for now, could be passed from StartScreen if implemented
            userName = "Player",
            onHome = { currentScreen = Screen.Start },
            onExit = { activity.finish() }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppNavigationHostPreview() {
    BrainTeaserTheme {

        AppNavigationHost()
    }
}