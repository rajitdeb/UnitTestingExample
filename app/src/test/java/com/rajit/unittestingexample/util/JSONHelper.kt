package com.rajit.unittestingexample.util

import java.io.InputStreamReader
import kotlin.text.StringBuilder

object JHelper {

    fun readFileResource(fileName: String): String {

        val inputStream = Helper::class.java.getResourceAsStream(fileName)

        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")

        reader.readLines().forEach {
            builder.append(it)
        }

        return builder.toString()
    }

}