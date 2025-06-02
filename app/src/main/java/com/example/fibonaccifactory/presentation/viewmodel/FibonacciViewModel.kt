package com.example.fibonaccifactory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fibonaccifactory.domain.model.FibonacciResult
import com.example.fibonaccifactory.domain.model.FibonacciSummary
import com.example.fibonaccifactory.domain.repository.FibonacciSummaryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FibonacciState {
    data object Loading : FibonacciState()
    data class Success(val results: List<FibonacciResult>) : FibonacciState()
    data class Error(val message: String) : FibonacciState()
}

class FibonacciViewModel(
    private val summaryRepository: FibonacciSummaryRepository
) : ViewModel() {
    private val _state = MutableStateFlow<FibonacciState>(FibonacciState.Success(emptyList()))
    val state: StateFlow<FibonacciState> = _state.asStateFlow()

    fun calculateAndUpdateSequence(n: Int) {
        viewModelScope.launch {
            try {
                _state.value = FibonacciState.Loading
                delay(150)
                
                if (n > 45) {
                    throw IllegalArgumentException("Número muito grande! Use um valor menor que 46.")
                }
                
                val sequence = generateFibonacciSequence(n)
                _state.value = FibonacciState.Success(sequence)

                val totalTime = sequence.sumOf { it.timestamp }
                val summary = FibonacciSummary(n, totalTime)
                summaryRepository.saveSummary(summary)
            } catch (e: Exception) {
                _state.value = FibonacciState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

    fun clearResults() {
        _state.value = FibonacciState.Success(emptyList())
    }

    private fun generateFibonacciSequence(n: Int): List<FibonacciResult> {
        if (n < 0) throw IllegalArgumentException("O número deve ser positivo")

        val results = mutableListOf<FibonacciResult>()
        results.add(FibonacciResult(0, 0))

        if (n == 0) return results

        results.add(FibonacciResult(1, 1))
        if (n == 1) return results

        var a = 0L
        var b = 1L

        for (i in 2..n) {
            val temp = a + b
            a = b
            b = temp
            results.add(FibonacciResult(i, b))
        }

        return results
    }
}