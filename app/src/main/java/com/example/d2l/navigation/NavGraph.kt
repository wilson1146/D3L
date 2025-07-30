package com.example.d2l.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.d2l.ui.screens.HomeScreen
import com.example.d2l.ui.screens.LabReportInstructionsScreen
import com.example.d2l.ui.screens.LabScreen
import com.example.d2l.ui.screens.LearningModulesScreen
import com.example.d2l.ui.screens.LoginScreen
import com.example.d2l.ui.screens.PdfViewerScreen
import com.example.d2l.ui.screens.ProjectInstructionsScreen
import com.example.d2l.ui.screens.RegistrationScreen
import com.example.d2l.ui.screens.StartHereScreen
import com.example.d2l.ui.screens.SyllabusScreen
import com.example.d2l.ui.screens.WeekScreen
import com.example.d2l.ui.screens.WelcomeScreen


@Composable
fun MyCourseApp() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "login_screen") {
            composable("start_here") { StartHereScreen(navController) }
            composable("learning_modules") { LearningModulesScreen(navController) }
            composable("login_screen") {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("home_screen") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNavigateToSignUp = {
                        navController.navigate("signup_screen")
                    }
                )
            }
            composable("home_screen") { HomeScreen(navController) }
            composable("welcome") { WelcomeScreen(navController) }

            composable("signup_screen") {
                RegistrationScreen(
                    onSignUpSuccess = {

                        navController.navigate("login_screen") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }

                    },
                    onNavigateToLogin = {
                        navController.navigate("login_screen") {

                        }
                    }
                )
            }

            composable("syllabus") { SyllabusScreen(navController) }
            composable("project_instruction_screen") { ProjectInstructionsScreen(navController) }
            composable("lab_report_instructions_screen") { LabReportInstructionsScreen(navController) }

            composable("week/{weekNumber}") { backStackEntry ->
                val weekNumber = backStackEntry.arguments?.getString("weekNumber")?.toIntOrNull() ?: 1
                WeekScreen(weekNumber = weekNumber, navController = navController)
            }
            composable("slides_pdf/{pdfAssetName}") { backStackEntry ->
                val assetName = backStackEntry.arguments?.getString("pdfAssetName") ?: "week_1"
                PdfViewerScreen(pdfAssetName = assetName) {
                    navController.popBackStack()
                }
            }
            composable(
                route = "lab/{weekNumber}",
                arguments = listOf(navArgument("weekNumber") { type = NavType.IntType })
            ) { backStackEntry ->
                val weekNumber = backStackEntry.arguments?.getInt("weekNumber") ?: 1
                LabScreen(weekNumber = weekNumber, navController = navController)
            }
        }
    }
}
