package com.rajit.unittestingexample.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineUtil(
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getUsername(): String {
        delay(10000)
        return "Hey! I was fetched after 10K ms"
    }

    suspend fun getUser(): String {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
        }
        return "User: Rajit Deb"
    }

    suspend fun getAddress(): String {
        withContext(dispatcher) {
            delay(5000)
        }
        return "Address: Chandni Chowk, Delhi"
    }

}