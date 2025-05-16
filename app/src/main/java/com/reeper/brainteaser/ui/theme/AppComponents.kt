package com.reeper.brainteaser.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reeper.brainteaser.R

/**
 * Reusable composable function for the standard application header.
 * Displays a greeting and a user profile icon.
 *
 * @param userName The name of the user to display in the greeting.
 */
@Composable
fun AppHeader(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column {
            Text(
                text = "Hello, $userName 👋",
                color = AppColors.Primary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Test how smart you are",
                color = AppColors.PrimaryVariant,
                fontSize = 14.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.user_profile_icon),
            contentDescription = "User Profile",
            modifier = Modifier.size(36.dp)
        )
    }
}