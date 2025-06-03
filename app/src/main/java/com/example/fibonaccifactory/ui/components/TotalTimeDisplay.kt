package com.example.fibonaccifactory.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fibonaccifactory.presentation.viewmodel.FibonacciState
import com.example.fibonaccifactory.presentation.viewmodel.FibonacciViewModel
import kotlin.math.roundToInt

@Composable
fun TotalTimeDisplay(viewModel: FibonacciViewModel) {
    val state by viewModel.state.collectAsState()
    
    if (state is FibonacciState.Success) {
        val totalTime = (state as FibonacciState.Success).totalCalculationTime
        if ((state as FibonacciState.Success).results.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Calculation Time",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = formatTime(totalTime),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

private fun formatTime(nanoseconds: Long): String {
    return when {
        nanoseconds < 1_000L -> "${nanoseconds}ns"
        nanoseconds < 1_000_000L -> "${(nanoseconds / 1_000.0).roundToInt()}µs"
        nanoseconds < 1_000_000_000L -> "${(nanoseconds / 1_000_000.0).roundToInt()}ms"
        else -> "${(nanoseconds / 1_000_000_000.0).roundToInt()}s"
    }
} 