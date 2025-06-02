package com.example.fibonaccifactory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fibonaccifactory.domain.model.FibonacciSummary
import com.example.fibonaccifactory.domain.repository.FibonacciSummaryRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted

class SummaryViewModel(
    private val summaryRepository: FibonacciSummaryRepository
) : ViewModel() {
    val summaries: StateFlow<List<FibonacciSummary>> = summaryRepository
        .getAllSummaries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
