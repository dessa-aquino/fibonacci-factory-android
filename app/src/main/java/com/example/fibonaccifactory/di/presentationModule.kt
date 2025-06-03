package com.example.fibonaccifactory.di

import com.example.fibonaccifactory.presentation.viewmodel.FibonacciViewModel
import com.example.fibonaccifactory.presentation.viewmodel.SummaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        FibonacciViewModel(
            calculateFibonacciUseCase = get(),
            summaryRepository = get()
        )
    }

    viewModel {
        SummaryViewModel(
            summaryRepository = get()
        )
    }
}