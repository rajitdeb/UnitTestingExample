package com.rajit.unittestingexample.util

sealed class NetworkResult<T>(val data: T? = null, val msg: String? = null) {

    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null): NetworkResult<T>(data, message)
    class Loading<T>: NetworkResult<T>()

}