package com.rajit.unittestingexample

import com.rajit.unittestingexample.util.Helper
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value= Parameterized::class)
class ReverseAStringTest(
    private val input: String,
    private val expected: String
) {

    private lateinit var helper: Helper

    @Before
    fun setup() {
        helper = Helper()
    }

    @Test
    fun testReverseAString_inputOriginalString_expectedReversedString() {
        val result = helper.reverseAString(input)
        assertEquals(expected, result)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name="{index} : {0} is valid - {1}")
        fun data(): List<Any> {
            return listOf(
                arrayOf("rajit", "tijar"),
                arrayOf("geeks", "skeeg"),
                arrayOf("abc", "cba"),
                arrayOf("Dolphin", "nihploD"),
                arrayOf("Potassium", "muissatoP"),
            )
        }

    }

}