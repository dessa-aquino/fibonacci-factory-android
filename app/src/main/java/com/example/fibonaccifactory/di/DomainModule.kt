package com.example.fibonaccifactory.di

import com.example.fibonaccifactory.domain.usecase.CalculateFibonacciUseCase
import org.koin.dsl.module

val domainModule = module {
    single { CalculateFibonacciUseCase() }
} 