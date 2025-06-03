package com.example.fibonaccifactory.di

import com.example.fibonaccifactory.data.repository.LocalFibonacciSummaryRepositoryImpl
import com.example.fibonaccifactory.domain.repository.FibonacciSummaryRepository
import org.koin.dsl.module

val appModule = module {

    single<FibonacciSummaryRepository> { LocalFibonacciSummaryRepositoryImpl() }
}