package com.rajit.unittestingexample.util

class Helper {

    fun isStringEqual(input: String, expected: String): Boolean {
        return input == expected
    }

    fun addTwoIntNumbersReturnSum(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    fun isNumberPalindrome(num: Long): Boolean {

        var number = num
        var reverse: Long = 0

        while(number != 0.toLong()) {

            val digit = number % 10
            reverse = (reverse * 10) + digit
            number /= 10

        }

        return reverse == num

    }

    fun isValidPassword(passString: String) : Boolean {

        if(passString.isNotEmpty() && passString.length in 6..15) {
            return true
        }

        return false

    }

    fun reverseAString(string: String): String {

        var reversedString = ""

        for (ch in string) {
            reversedString = ch + reversedString
        }

        return reversedString

    }

}