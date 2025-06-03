package com.example.fibonaccifactory.domain.usecase

import com.example.fibonaccifactory.domain.model.FibonacciResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalculateFibonacciUseCase : UseCase<Int, List<FibonacciResult>> {

    override suspend fun invoke(params: Int): List<FibonacciResult> {
        return withContext(Dispatchers.Default) {
            calculateFibonacciSequence(params)
        }
    }

    private fun calculateFibonacciSequence(n: Int): List<FibonacciResult> {
        if (n < 0) throw IllegalArgumentException("Index cannot be negative")

        val results = mutableListOf<FibonacciResult>()
        
        // Primeiro número (0)
        val startTime0 = System.nanoTime()
        results.add(FibonacciResult(
            index = 0,
            value = 0,
            timestamp = System.nanoTime() - startTime0 // Mantém em nanosegundos
        ))

        if (n == 0) return results

        // Segundo número (1)
        val startTime1 = System.nanoTime()
        results.add(FibonacciResult(
            index = 1,
            value = 1,
            timestamp = System.nanoTime() - startTime1 // Mantém em nanosegundos
        ))

        if (n == 1) return results

        // Resto da sequência
        var prev = 0L
        var current = 1L

        for (i in 2..n) {
            val startTime = System.nanoTime()
            val next = prev + current
            prev = current
            current = next
            
            results.add(FibonacciResult(
                index = i,
                value = current,
                timestamp = System.nanoTime() - startTime // Mantém em nanosegundos
            ))
        }

        return results
    }
} 