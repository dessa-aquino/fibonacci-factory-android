package com.example.fibonaccifactory.presentation.viewmodel

import com.example.fibonaccifactory.domain.model.FibonacciResult
import com.example.fibonaccifactory.domain.repository.FibonacciSummaryRepository
import com.example.fibonaccifactory.domain.usecase.CalculateFibonacciUseCase
import com.example.fibonaccifactory.presentation.state.FibonacciState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FibonacciViewModelTest {

    private lateinit var viewModel: FibonacciViewModel
    private lateinit var useCase: CalculateFibonacciUseCase
    private lateinit var repository: FibonacciSummaryRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = mockk()
        repository = mockk(relaxed = true)
        viewModel = FibonacciViewModel(useCase, repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be empty success`() = runTest {
        val currentState = viewModel.state.value
        assertTrue(currentState is FibonacciState.Success)
        assertEquals(0, (currentState as FibonacciState.Success).results.size)
        assertEquals(0, currentState.totalCalculationTime)
    }

    @Test
    fun `when calculation succeeds, should update state with results`() = runTest {
        val testResults = listOf(
            FibonacciResult(0, 0, 100),
            FibonacciResult(1, 1, 100)
        )
        coEvery { useCase(5) } returns testResults

        viewModel.calculateAndUpdateSequence(5)
        testDispatcher.scheduler.advanceUntilIdle()

        val currentState = viewModel.state.value
        assertTrue(currentState is FibonacciState.Success)
        assertEquals(testResults, (currentState as FibonacciState.Success).results)
        assertEquals(200, currentState.totalCalculationTime)
    }

    @Test
    fun `when calculation fails with IllegalArgumentException, should update state with error`() = runTest {
        val errorMessage = "Number cannot be negative"
        coEvery { useCase(any()) } throws IllegalArgumentException(errorMessage)

        viewModel.calculateAndUpdateSequence(-1)
        testDispatcher.scheduler.advanceUntilIdle()

        val currentState = viewModel.state.value
        assertTrue(currentState is FibonacciState.Error)
        assertEquals(errorMessage, (currentState as FibonacciState.Error).message)
    }

    @Test
    fun `when calculation fails with unexpected error, should update state with error`() = runTest {
        coEvery { useCase(any()) } throws RuntimeException("Unexpected error")

        viewModel.calculateAndUpdateSequence(5)
        testDispatcher.scheduler.advanceUntilIdle()

        val currentState = viewModel.state.value
        assertTrue(currentState is FibonacciState.Error)
        assertTrue((currentState as FibonacciState.Error).message.contains("unexpected error"))
    }

    @Test
    fun `when clearing results, should reset to initial state`() = runTest {
        coEvery { useCase(5) } returns listOf(FibonacciResult(0, 0, 100))
        
        viewModel.calculateAndUpdateSequence(5)
        testDispatcher.scheduler.advanceUntilIdle()
        viewModel.clearResults()

        val currentState = viewModel.state.value
        assertTrue(currentState is FibonacciState.Success)
        assertTrue((currentState as FibonacciState.Success).results.isEmpty())
        assertEquals(0, currentState.totalCalculationTime)
    }

    @Test
    fun `when calculation succeeds, should save summary`() = runTest {
        val testResults = listOf(
            FibonacciResult(0, 0, 100),
            FibonacciResult(1, 1, 100)
        )
        coEvery { useCase(5) } returns testResults

        viewModel.calculateAndUpdateSequence(5)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.saveSummary(any()) }
    }
} 