package com.example.fibonaccifactory.domain.repository

import com.example.fibonaccifactory.domain.model.FibonacciSummary
import kotlinx.coroutines.flow.Flow

interface FibonacciSummaryRepository {
    suspend fun saveSummary(summary: FibonacciSummary)
    fun getAllSummaries(): Flow<List<FibonacciSummary>>
}
