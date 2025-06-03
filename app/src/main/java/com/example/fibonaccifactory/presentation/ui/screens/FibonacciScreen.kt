package com.example.fibonaccifactory.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fibonaccifactory.presentation.viewmodel.FibonacciViewModel
import com.example.fibonaccifactory.presentation.ui.components.FibonacciInput
import com.example.fibonaccifactory.presentation.ui.components.FibonacciResultList
import com.example.fibonaccifactory.presentation.ui.components.FibonacciTopBar
import com.example.fibonaccifactory.presentation.ui.components.TotalTimeDisplay
import org.koin.androidx.compose.koinViewModel

@Composable
fun FibonacciScreen(
    onNavigateToSummary: () -> Unit
) {
    val viewModel: FibonacciViewModel = koinViewModel()
    var textInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = { 
            FibonacciTopBar(
                onNavigateToSummary = onNavigateToSummary,
                viewModel = viewModel,
                onTextInputChange = { textInput = it }
            ) 
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Calculate Fibonacci Sequence",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            FibonacciInput(viewModel, textInput) { textInput = it }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Results",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                FibonacciResultList(viewModel)
            }
            
            TotalTimeDisplay(viewModel)
        }
    }
} 