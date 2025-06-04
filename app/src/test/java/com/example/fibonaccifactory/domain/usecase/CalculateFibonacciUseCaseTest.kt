package com.example.fibonaccifactory.domain.usecase

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class CalculateFibonacciUseCaseTest {

    private lateinit var useCase: CalculateFibonacciUseCase

    @Before
    fun setup() {
        useCase = CalculateFibonacciUseCase()
    }

    @Test
    fun `when input is 0, should return list with single element 0`() = runBlocking {
        val result = useCase(0)
        assertEquals(1, result.size)
        assertEquals(0, result[0].value)
        assertEquals(0, result[0].index)
    }

    @Test
    fun `when input is 1, should return list with elements 0 and 1`() = runBlocking {
        val result = useCase(1)
        assertEquals(2, result.size)
        assertEquals(0, result[0].value)
        assertEquals(1, result[1].value)
    }

    @Test
    fun `when input is 5, should return correct fibonacci sequence`() = runBlocking {
        val result = useCase(5)
        val expectedValues = listOf(0L, 1L, 1L, 2L, 3L, 5L)
        assertEquals(expectedValues.size, result.size)
        result.forEachIndexed { index, fibResult ->
            assertEquals(expectedValues[index], fibResult.value)
            assertEquals(index, fibResult.index)
            assertTrue(fibResult.timestamp >= 0)
        }
    }

    @Test
    fun `when input is negative, should throw IllegalArgumentException`() = runBlocking {
        try {
            useCase(-1)
            fail("Expected IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            // Test passed
        }
    }

    @Test
    fun `when input is above 92, should throw IllegalArgumentException`() = runBlocking {
        try {
            useCase(93)
            fail("Expected IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            // Test passed
        }
    }

    @Test
    fun `should measure execution time for each number`() = runBlocking {
        val result = useCase(5)
        result.forEach { fibResult ->
            assertTrue(fibResult.timestamp > 0)
        }
    }
} 