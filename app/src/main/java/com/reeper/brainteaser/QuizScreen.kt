package com.reeper.brainteaser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reeper.brainteaser.ui.theme.*
import kotlinx.coroutines.delay

// Data class to represent a single quiz question
data class Question(
    val imageRes: Int, // Resource ID for the question image
    val text: String, // The question text
    val answer: Boolean // The correct answer (true or false)
)

// Sample list of questions for the quiz
val sampleQuestions = listOf(
    Question(R.drawable.q1, "Is the Great Wall of China in Japan?", false),
    Question(R.drawable.q2, "Is the capital of France Paris?", true),
    Question(R.drawable.q3, "Do penguins fly?", false),
    Question(R.drawable.q4, "Is water made of H2O?", true),
    Question(R.drawable.q5, "Is the sun a planet?", false)
)


@Composable
fun QuizScreen(onQuizComplete: (Int) -> Unit) {
    // State to hold the shuffled list of questions. Remembered across recompositions.
    val questions = remember { sampleQuestions.shuffled() }
    // State to track the index of the current question being displayed.
    var currentQuestionIndex by remember { mutableStateOf(0) }
    // State to control whether the result feedback (Correct/Incorrect) is shown.
    var showResult by remember { mutableStateOf(false) }
    // State to indicate if the user's answer was correct.
    var isAnswerCorrect by remember { mutableStateOf(false) }
    // State for the countdown timer value.
    var timeLeft by remember { mutableStateOf(5) }
    // State to track if the user has answered the current question.
    var answered by remember { mutableStateOf(false) }
    // State to track the user's overall score.
    var score by remember { mutableStateOf(0) }

    // Total number of questions
    val totalQuestions = questions.size
    // Get the current question based on the index
    val currentQuestion = questions[currentQuestionIndex]

    // Determine the color for the result feedback based on correctness
    val resultColor = if (isAnswerCorrect) AppColors.Correct else AppColors.Incorrect

    // LaunchedEffect to manage the timer lifecycle.
    // It restarts whenever the currentQuestionIndex changes.
    LaunchedEffect(currentQuestionIndex) {
        // Reset timer and state for the new question
        timeLeft = 5
        answered = false
        showResult = false
        // Countdown loop
        while (timeLeft > 0 && !answered) {
            delay(1000L) // Wait for 1 second
            timeLeft--
        }
        // If time runs out and the user hasn't answered
        if (!answered) {
            showResult = true // Show result
            isAnswerCorrect = false // Mark as incorrect
        }
    }

    // Main column layout for the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Using a direct color for background
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween // Space out content vertically
    ) {
        // Top section (Header and question area)

            // Reusable Header composable
            AppHeader(userName = "Player") // Hardcoded Player for now

            Spacer(modifier = Modifier.height(16.dp))

            // Box to contain the image and question text overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp) // Height for the image container
            ) {
                // Question Image
                Image(
                    painter = painterResource(id = currentQuestion.imageRes),
                    contentDescription = null, // Content description for images is good practice
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)) // Clip image to rounded corners
                )
                // Overlay Box for the question text
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter) // Align text Box to bottom of the parent Box
                        .background(AppColors.Primary) // Using color from centralized constants
                        .padding(12.dp)
                ) {
                    // Question Text
                    Text(
                        text = currentQuestion.text,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.align(Alignment.Center), // Center text within the Box
                        textAlign = TextAlign.Center // Center align text content
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress bar indicating quiz completion
            LinearProgressIndicator(
                progress = (currentQuestionIndex + 1) / totalQuestions.toFloat(),
                color = AppColors.Primary, // Using color from centralized constants
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Countdown Timer display
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.Primary, RoundedCornerShape(8.dp)) // Using color from centralized constants
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Time Left:",
                        color = AppColors.Primary, // Using color from centralized constants
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Timer text, formatted to always show two digits for seconds
                    Text(
                        text = "00:%02d".format(timeLeft), // Corrected time format
                        color = AppColors.Primary, // Using color from centralized constants
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Answer buttons (True/False)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // True Button
                Button(
                    // Only allow clicking if not already answered and result is not shown
                    onClick = {
                        if (!answered && !showResult) {
                            answered = true
                            isAnswerCorrect = currentQuestion.answer == true // Check if answer is True
                            // Increment score if correct
                            if (isAnswerCorrect) {
                                score++
                            }
                            showResult = true // Show the result feedback
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary), // Using color from centralized constants
                    modifier = Modifier.weight(1f),
                    enabled = !answered && !showResult // Disable button after answering or showing result
                ) {
                    Text(
                        text = "True",
                        color = Color.White
                    )
                }

                // False Button
                Button(
                    // Only allow clicking if not already answered and result is not shown
                    onClick = {
                        if (!answered && !showResult) {
                            answered = true
                            isAnswerCorrect = currentQuestion.answer == false // Check if answer is False
                            // Increment score if correct
                            if (isAnswerCorrect) {
                                score++
                            }
                            showResult = true // Show the result feedback
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.Secondary), // Using color from centralized constants
                    modifier = Modifier.weight(1f),
                    enabled = !answered && !showResult // Disable button after answering or showing result
                ) {
                    Text(
                        text = "False",
                        color = Color.White
                    )
                }
            }

            // Spacer after buttons, adjusted based on whether result is show
            Spacer(modifier = Modifier
                .height(if (showResult) 8.dp else 16.dp))


        // Result section (conditionally displayed)
        if (showResult) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Box for the result feedback text (Correct/Incorrect)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(resultColor, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isAnswerCorrect) "😊 Correct" else "😞 Incorrect",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    // Prompt text - could be conditional or moved
                    text = "Ready for the next challenge?",
                    color = AppColors.PrimaryVariant,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )


                Spacer(modifier = Modifier.height(12.dp))

                // Next Button
                // This button now handles moving to the next question OR finishing the quiz
                OutlinedButton(
                    onClick = {
                        // Check if there are more questions
                        if (currentQuestionIndex < questions.lastIndex) {
                            // Move to the next question
                            currentQuestionIndex++
                            // showResult and answered are reset by LaunchedEffect
                        } else {
                            // Quiz finished, call the onQuizComplete lambda with the final score
                            onQuizComplete(score)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary), // Using color from centralized constants
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        // Button text changes depending on whether it's the last question
                        text = if (currentQuestionIndex < questions.lastIndex) "Next Question →" else "Finish Quiz →",
                        color = Color.White, // Using color from centralized constants
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Spacer at the very bottom when result is shown
            }
        }
        // Spacer to fill remaining space when result is not shown, keeping content aligned top
        if (!showResult) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    BrainTeaserTheme {
        // Pass a dummy lambda for the preview
        QuizScreen(onQuizComplete = { finalScore ->
            println("Quiz Finished with score: $finalScore")
        })
    }
}