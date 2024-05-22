package com.example.magicenglish.test_trainer.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.magicenglish.ui.theme.DarkBlue
import com.example.magicenglish.ui.theme.DarkRed
import com.example.magicenglish.ui.theme.DeepSkyBlue
import com.example.magicenglish.ui.theme.LawnGreen
import com.example.magicenglish.ui.theme.Orange


@Composable
fun TestCompleted(
    navController: NavController,
    correctAnswer: Int?,
    totalQuestions: Int?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            DarkBlue,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Test completed!  ")
                    }
                    withStyle(
                        style = SpanStyle(
                            DeepSkyBlue,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(" $correctAnswer/$totalQuestions")
                    }
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 60.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.height(56.dp),
                    onClick = {
                        navController.navigate(Screens.QuizHome.name)
                    }
                ) {
                    Text("Back to list", fontSize = 20.sp)
                }
            }
        }
    }
}