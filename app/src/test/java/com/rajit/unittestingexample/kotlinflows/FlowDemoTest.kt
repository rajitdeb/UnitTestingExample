package com.rajit.unittestingexample.kotlinflows

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class FlowDemoTest {

    @Test
    fun test_getFlow() = runTest {
        val systemUnderTest = FlowDemo()
        val result = systemUnderTest.getFlow().toList()
        assertEquals(listOf(1, 2), result)
    }

}