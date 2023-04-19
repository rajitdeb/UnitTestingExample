package com.rajit.unittestingexample.repository

import com.rajit.unittestingexample.model.LOGIN_STATUS
import com.rajit.unittestingexample.model.User

class UserRepository {

    private val listOfUsers = listOf<User>(
        User(1, "John Doe", "john_doe@gmail.com", "24johnDoe@1"),
        User(2, "Jane Doe", "ijane_doe@gmail.com", "iJaneDoe@69"),
        User(1, "Cane Doe", "heycane@outlook.com", "cane999"),
        User(1, "Kevin Doe", "kevin_69@example.com", "kevin6969@$"),
    )

    fun loginUser(
        email: String,
        password:String
    ): LOGIN_STATUS {

        // Fetch User from DB
        val users = listOfUsers.filter { user -> user.email == email }
        return if(users.size == 1) {
            if(users[0].password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }

    }

}