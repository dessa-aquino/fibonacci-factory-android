package com.example.fibonaccifactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fibonaccifactory.presentation.navigation.AppNavigation
import com.example.fibonaccifactory.presentation.ui.theme.FibonacciFactoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FibonacciFactoryTheme {
                AppNavigation()
            }
        }
    }
}
