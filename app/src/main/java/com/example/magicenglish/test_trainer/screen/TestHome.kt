package com.example.magicenglish.test_trainer.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.magicenglish.ui.theme.DarkBlue
import com.example.magicenglish.ui.theme.PaleGoldenrod

@Composable
fun QuizHome(
    navController: NavController,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    val questionItems = viewModel.data.value.data?.toMutableList()
    var correctAnswer by remember { mutableStateOf(0) }
    var currentQuestionIndex by remember { mutableStateOf(viewModel.currentQuestionIndex) }
    val totalQuestion = questionItems?.size

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val showIntermediateResult = remember { mutableStateOf(false) }

        if (showIntermediateResult.value) {
            IntermediateResult(
                correctAnswers = correctAnswer,
                onContinue = {
                    showIntermediateResult.value = false
                    viewModel.currentQuestionIndex = currentQuestionIndex + 1
                    navController.popBackStack()
                    navController.navigate(Screens.QuizHome.name)
                }
            )
        } else {
            val questionItem = questionItems?.get(currentQuestionIndex - 1)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 50.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "Question $currentQuestionIndex/$totalQuestion",
                    fontWeight = FontWeight.W900,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.background
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (questionItem != null) {
                    Text(
                        text = questionItem.question,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))

                questionItem?.options?.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                            .height(50.dp)
                            .background(Color.Transparent)
                            .border(2.dp, Color.Blue, RoundedCornerShape(15.dp))
                            .clip(RoundedCornerShape(50))
                            .clickable {
                                if (option == questionItem.correctAnswer) correctAnswer++
                                if (currentQuestionIndex % 5 == 0 && currentQuestionIndex != totalQuestion) {
                                    navController.navigate(Screens.IntermediateResult.name + "/${correctAnswer}/${currentQuestionIndex}")
                                } else {
                                    if (currentQuestionIndex == totalQuestion) {
                                        navController.navigate(Screens.QuizEnd.name + "/${correctAnswer}/${totalQuestion}")
                                    } else {
                                        currentQuestionIndex++
                                        viewModel.currentQuestionIndex = currentQuestionIndex
                                    }
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 17.dp),
                            text = option,
                            color = Color.Black,
                        )
                    }
                }
            }
        }
    }
}
