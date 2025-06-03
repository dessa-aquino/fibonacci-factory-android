package com.example.fibonaccifactory.domain.usecase

interface UseCase<in Params, out Type> {
    suspend operator fun invoke(params: Params): Type
}

interface NoParamsUseCase<out Type> {
    suspend operator fun invoke(): Type
} 