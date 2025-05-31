package com.example.fibonaccifactory.presentation.navigation

sealed class NavRoute(val route: String) {
    object Home : NavRoute("home")
    object Summary : NavRoute("summary")
}
