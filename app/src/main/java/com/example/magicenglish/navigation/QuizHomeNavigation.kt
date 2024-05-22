package com.example.magicenglish.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.magicenglish.test_trainer.screen.IntermediateResult
import com.example.magicenglish.test_trainer.screen.QuestionsViewModel
import com.example.magicenglish.test_trainer.screen.QuizHome
import com.example.magicenglish.test_trainer.screen.Screens
import com.example.magicenglish.test_trainer.screen.TestCompleted


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuizHomeNavigation() {
    val viewModel = viewModel<QuestionsViewModel>()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.QuizHome.name
    ) {
        composable(Screens.QuizHome.name) {
            QuizHome(
                navController,
                viewModel
            )
        }
        composable(
            //путь к маршруту
            //переменные частью URl
            Screens.QuizEnd.name + "/{correctAnswer}/{totalQuestion}",
            arguments = listOf(
                //список аргументов ожидаемых в этом маршруте
                navArgument("correctAnswer") {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("totalQuestion") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            TestCompleted(
                navController = navController,
                //backStackEntry для получения значения пар
                correctAnswer = backStackEntry.arguments?.getInt("correctAnswer") ?: 0,
                totalQuestions = backStackEntry.arguments?.getInt("totalQuestion") ?: 0
            )
        }
        composable(
            Screens.IntermediateResult.name + "/{correctAnswers}/{currentQuestionIndex}",
            arguments = listOf(
                navArgument("correctAnswers") {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("currentQuestionIndex") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) { backStackEntry ->
            val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0
            val currentQuestionIndex = backStackEntry.arguments?.getInt("currentQuestionIndex") ?: 1
            IntermediateResult(
                correctAnswers = correctAnswers,
                onContinue = {
                    viewModel.currentQuestionIndex = currentQuestionIndex + 1
                    navController.popBackStack(Screens.QuizHome.name, inclusive = false)
                }
            )
        }
    }
}

