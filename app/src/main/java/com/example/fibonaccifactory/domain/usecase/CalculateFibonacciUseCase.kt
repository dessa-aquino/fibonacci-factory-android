package com.example.fibonaccifactory.domain.usecase

import com.example.fibonaccifactory.domain.model.FibonacciResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CalculateFibonacciUseCase : UseCase<Int, List<FibonacciResult>> {

    override suspend fun invoke(params: Int): List<FibonacciResult> {
        return withContext(Dispatchers.Default) {
            delay(1000) // 1 second delay
            calculateFibonacciSequence(params)
        }
    }

    private fun calculateFibonacciSequence(n: Int): List<FibonacciResult> {
        var lastTimestamp = System.nanoTime()
        val results = mutableListOf<FibonacciResult>()

        when {
            n < 0 -> throw IllegalArgumentException("Number cannot be negative")
            n > 92 -> throw IllegalArgumentException("Numbers above 92 will cause overflow. Please use a smaller number")
        }

        var currentTimestamp = System.nanoTime()
        val interval0 = (currentTimestamp - lastTimestamp) / 1_000_000.0
        lastTimestamp = currentTimestamp
        
        results.add(FibonacciResult(
            index = 0,
            value = 0,
            timeInterval = interval0
        ))

        if (n == 0) return results

        currentTimestamp = System.nanoTime()
        val interval1 = (currentTimestamp - lastTimestamp) / 1_000_000.0
        lastTimestamp = currentTimestamp
        
        results.add(FibonacciResult(
            index = 1,
            value = 1,
            timeInterval = interval1
        ))

        if (n == 1) return results

        var prev = 0L
        var current = 1L

        for (i in 2..n) {
            val next = prev + current
            prev = current
            current = next

            currentTimestamp = System.nanoTime()
            val intervalN = (currentTimestamp - lastTimestamp) / 1_000_000.0 // Convert nanoseconds to milliseconds with decimals
            lastTimestamp = currentTimestamp
            
            results.add(FibonacciResult(
                index = i,
                value = current,
                timeInterval = intervalN
            ))
        }

        return results
    }
} 