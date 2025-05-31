package com.example.fibonaccifactory.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fibonaccifactory.FibonacciActivity
import com.example.fibonaccifactory.presentation.activities.SummaryActivity


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {
        composable(NavRoute.Home.route) {
            FibonacciActivity(
                onNavigateToSummary = {
                    navController.navigate(NavRoute.Summary.route)
                }
            )
        }
        composable(NavRoute.Summary.route) {
            SummaryActivity(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}