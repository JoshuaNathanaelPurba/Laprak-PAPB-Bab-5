package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.AboutScreen
import com.example.myapplication.ui.screen.DetailScreen
import com.example.myapplication.ui.screen.HomeScreen
import com.example.myapplication.ui.screen.ProfileScreen

@Composable
fun NavigationLabApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        // =========================
        // HOME
        // =========================

        composable(Routes.HOME) {

            HomeScreen(
                onOpenDetail = { id ->
                    navController.navigate(
                        Routes.detail(id)
                    )
                },

                onOpenProfile = {
                    navController.navigate(
                        Routes.PROFILE
                    )
                },

                onOpenAbout = {
                    navController.navigate(
                        Routes.ABOUT
                    )
                }
            )
        }


        // =========================
        // DETAIL
        // =========================

        composable(
            route = Routes.DETAIL,

            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            )

        ) { backStackEntry ->

            val studentId =
                backStackEntry.arguments?.getInt("studentId") ?: 0

            DetailScreen(
                studentId = studentId,
                onBack = {
                    navController.popBackStack()
                },
                onOpenProfile = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }


        // =========================
        // PROFILE
        // =========================

        composable(Routes.PROFILE) {

            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }


        // =========================
        // ABOUT
        // =========================

        composable(Routes.ABOUT) {

            AboutScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}