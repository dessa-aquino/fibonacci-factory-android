package com.example.fibonaccifactory.domain.model

data class FibonacciResult(
    val index: Int,
    val value: Int,
    val timestamp: Long = System.currentTimeMillis()
)