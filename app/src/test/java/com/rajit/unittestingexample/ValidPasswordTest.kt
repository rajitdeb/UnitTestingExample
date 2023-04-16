package com.rajit.unittestingexample

import com.rajit.unittestingexample.util.Helper
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(value=Parameterized::class)
class ValidPasswordTest(
    private val input: String,
    private val expected: Boolean
) {

    private lateinit var helper: Helper

    @Before
    fun setup() {
        helper = Helper()
    }

    @Test
    fun testValidPassword_inputPasswordString_expectedBoolean() {
        val result = helper.isValidPassword(input)
        assertEquals(expected, result)
    }

    companion object {

        @JvmStatic
        @Parameters(name="{index} : {0} is valid - {1}")
        fun data(): List<Any> {
            return listOf(
                arrayOf("abcd1", false),
                arrayOf("abcd1234", true),
                arrayOf("", false),
                arrayOf("abcd12345678901", true),
                arrayOf("abcd123456789012", false),
                arrayOf("abcd123456789012354", false)
            )
        }

    }

}