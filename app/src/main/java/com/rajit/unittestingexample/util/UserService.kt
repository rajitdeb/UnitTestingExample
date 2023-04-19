package com.rajit.unittestingexample.util

import com.rajit.unittestingexample.model.LOGIN_STATUS.*
import com.rajit.unittestingexample.repository.UserRepository

class UserService(
    private val userRepository: UserRepository
) {

    fun loginUser(email: String, password: String): String {

        val status = userRepository.loginUser(email, password)
        return when(status) {
            INVALID_USER -> "User doesn't exist!"
            INVALID_PASSWORD -> "Invalid Password"
            UNKNOWN_ERROR -> "Unknown error occurred"
            SUCCESS -> "Logged in Successfully"
        }

    }

}