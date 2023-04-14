package com.rajit.unittestingexample

import com.rajit.unittestingexample.util.Helper
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class HelperTest {

    private lateinit var helper: Helper

    @Before
    fun setup() {
        helper = Helper()
    }

    @Test
    fun isStringEqual() {
        val result = helper.isStringEqual("hello", "hello")
        TestCase.assertEquals(true, result)
    }

    @Test
    fun testAddTwoIntNumberReturnSum_inputInt_expectedSum() {
        val result = helper.addTwoIntNumbersReturnSum(3, 6)
        TestCase.assertEquals(9, result)
    }

    @Test
    fun testIsPalindrome_inputLong_expectedTrue() {
        val result = helper.isNumberPalindrome(1101011)
        TestCase.assertEquals(true, result)
    }

    @Test
    fun testIsPalindrome_inputLong_expectedFalse() {
        val result = helper.isNumberPalindrome(101011)
        TestCase.assertEquals(false, result)
    }

    @Test
    fun testIsPalindrome_inputLongShort_expectedTrue() {
        val result = helper.isNumberPalindrome(1)
        TestCase.assertEquals(true, result)
    }

    @Test
    fun testIsPalindrome_inputLongShort_expectedFalse() {
        val result = helper.isNumberPalindrome(1101)
        TestCase.assertEquals(false, result)
    }

}