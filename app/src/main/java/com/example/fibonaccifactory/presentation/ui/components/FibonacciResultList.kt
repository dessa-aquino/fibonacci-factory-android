package com.example.fibonaccifactory.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fibonaccifactory.presentation.state.FibonacciState
import com.example.fibonaccifactory.presentation.viewmodel.FibonacciViewModel

@Composable
fun FibonacciResultList(viewModel: FibonacciViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is FibonacciState.Loading -> {
            LoadingIndicator()
        }
        is FibonacciState.Error -> {
            ErrorDisplay((state as FibonacciState.Error).message)
        }
        is FibonacciState.Success -> {
            val results = (state as FibonacciState.Success).results
            if (results.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    items(results) { result ->
                        InfoCard(
                            leftText = "N: ${result.index}",
                            rightText = "${result.value}"
                        )
                    }
                }
            }
        }
    }
}