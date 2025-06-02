package com.example.fibonaccifactory.data.repository

import com.example.fibonaccifactory.domain.model.FibonacciSummary
import com.example.fibonaccifactory.domain.repository.FibonacciSummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LocalFibonacciSummaryRepositoryImpl() : FibonacciSummaryRepository {

    private var fibonacciSummaryList: MutableList<FibonacciSummary> = mutableListOf()

    override suspend fun saveSummary(summary: FibonacciSummary) {
        fibonacciSummaryList.add(summary)
    }

    override fun getAllSummaries(): Flow<List<FibonacciSummary>> {
        return flowOf(fibonacciSummaryList)
    }
}
