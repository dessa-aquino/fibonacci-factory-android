package com.example.fibonaccifactory.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fibonaccifactory.presentation.ui.screens.FibonacciScreen
import com.example.fibonaccifactory.presentation.ui.screens.SummaryScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {
        composable(NavRoute.Home.route) {
            FibonacciScreen(
                onNavigateToSummary = {
                    navController.navigate(NavRoute.Summary.route)
                }
            )
        }
        composable(NavRoute.Summary.route) {
            SummaryScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}