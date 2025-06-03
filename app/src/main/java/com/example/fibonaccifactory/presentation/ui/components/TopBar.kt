package com.example.fibonaccifactory.presentation.ui.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.fibonaccifactory.presentation.viewmodel.FibonacciViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FibonacciTopBar(
    onNavigateToSummary: () -> Unit,
    viewModel: FibonacciViewModel,
    onTextInputChange: (String) -> Unit
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            TextButton(
                onClick = {
                    viewModel.clearResults()
                    onTextInputChange("")
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text("Clean")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            TextButton(
                onClick = onNavigateToSummary,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text("Summary")
            }
        }
    )
} 