package com.rajit.unittestingexample

import com.rajit.unittestingexample.util.Helper
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

/*
* ALWAYS USE `RUN WITH COVERAGE` option to make sure all the test cases and lines are covered
* */
@RunWith(value = Parameterized::class)
class ParameterizedTest(
    private val input: Long,
    private val expected: Boolean
) {

    private lateinit var helper: Helper

    @Before
    fun setup() {
        helper = Helper()
    }

    @Test
    fun testIsNumberPalindrome() {
        val result = helper.isNumberPalindrome(input)
        assertEquals(expected, result)
    }

    companion object {

        @JvmStatic
        @Parameters(name = "{index} : {0} is palindrome - {1}")
        fun data(): List<Any> {
            return listOf(
                arrayOf(1101011, true),
                arrayOf(101011, false),
                arrayOf(1, true),
                arrayOf(1101, false)
            )
        }

    }

}