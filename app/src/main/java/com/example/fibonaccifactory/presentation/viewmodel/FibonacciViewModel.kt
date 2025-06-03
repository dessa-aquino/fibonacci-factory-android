package com.example.fibonaccifactory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fibonaccifactory.domain.model.FibonacciResult
import com.example.fibonaccifactory.domain.model.FibonacciSummary
import com.example.fibonaccifactory.domain.repository.FibonacciSummaryRepository
import com.example.fibonaccifactory.domain.usecase.CalculateFibonacciUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FibonacciState {
    data object Loading : FibonacciState()
    data class Success(
        val results: List<FibonacciResult>,
        val totalCalculationTime: Long
    ) : FibonacciState()
    data class Error(val message: String) : FibonacciState()
}

class FibonacciViewModel(
    private val calculateFibonacciUseCase: CalculateFibonacciUseCase,
    private val summaryRepository: FibonacciSummaryRepository
) : ViewModel() {
    private val _state = MutableStateFlow<FibonacciState>(FibonacciState.Success(emptyList(), 0))
    val state: StateFlow<FibonacciState> = _state.asStateFlow()

    fun calculateAndUpdateSequence(number: Int) {
        viewModelScope.launch {
            try {
                _state.value = FibonacciState.Loading
                
                val sequence = calculateFibonacciUseCase(number)
                val totalTime = calculateTotalTime(sequence)
                
                _state.value = FibonacciState.Success(
                    results = sequence,
                    totalCalculationTime = totalTime
                )

                val summary = FibonacciSummary(
                    n = number,
                    totalTime = totalTime
                )
                summaryRepository.saveSummary(summary)
            } catch (e: Exception) {
                _state.value = FibonacciState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    fun clearResults() {
        _state.value = FibonacciState.Success(emptyList(), 0)
    }

    private fun calculateTotalTime(results: List<FibonacciResult>): Long {
        return results.sumOf { it.timestamp }
    }
}