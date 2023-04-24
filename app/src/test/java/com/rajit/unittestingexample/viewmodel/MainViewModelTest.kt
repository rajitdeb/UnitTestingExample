package com.rajit.unittestingexample.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rajit.unittestingexample.repository.ProductRepository
import com.rajit.unittestingexample.util.NetworkResult
import com.rajit.unittestingexample.util.getOrAwaitValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: ProductRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getProducts() = runTest {

        Mockito.`when`(repository.getProducts())
            .thenReturn(NetworkResult.Success(emptyList()))

        val systemUnderTest = MainViewModel(repository)
        systemUnderTest.getProducts()

        testDispatcher.scheduler.advanceUntilIdle()

        val result = systemUnderTest.products.getOrAwaitValue()
        assertEquals(0, result.data!!.size)
    }

    @Test
    fun test_getProducts_expectedError() = runTest {
        Mockito.`when`(repository.getProducts())
            .thenReturn(NetworkResult.Error("Something went Wrong!!"))

        val systemUnderTest = MainViewModel(repository)
        systemUnderTest.getProducts()

        testDispatcher.scheduler.advanceUntilIdle()

        val result = systemUnderTest.products.getOrAwaitValue()
        assertEquals(true, result is NetworkResult.Error)
        assertEquals("Something went Wrong!!", result.msg)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}