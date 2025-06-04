package com.example.fibonaccifactory.presentation.state

import com.example.fibonaccifactory.domain.model.FibonacciResult

sealed class FibonacciState {
    data class Success(
        val results: List<FibonacciResult>,
        val totalCalculationTime: Long
    ) : FibonacciState()

    data class Error(val message: String) : FibonacciState()

    object Loading : FibonacciState()
} 