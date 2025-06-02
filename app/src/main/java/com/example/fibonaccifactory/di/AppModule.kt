package com.example.fibonaccifactory.di

import com.example.fibonaccifactory.data.repository.LocalFibonacciSummaryRepositoryImpl
import com.example.fibonaccifactory.domain.repository.FibonacciSummaryRepository
import com.example.fibonaccifactory.presentation.viewmodel.FibonacciViewModel
import com.example.fibonaccifactory.presentation.viewmodel.SummaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<FibonacciSummaryRepository> { LocalFibonacciSummaryRepositoryImpl() }
    
    viewModel { FibonacciViewModel(get()) }
    viewModel { SummaryViewModel(get()) }
}