package com.rajit.unittestingexample

import com.rajit.unittestingexample.model.LOGIN_STATUS
import com.rajit.unittestingexample.repository.UserRepository
import com.rajit.unittestingexample.util.UserService
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/*
    *   UserService class is dependent on UserRepository
    *   But in testing environment, test cases should not be dependent on anything
    *   So, we use Mockito to mock UserRepository Class Object
    *
    *   By default, all classes in Java are final
    *   Since, Mockito is in Java therefore we need to use the third-party kotlin library of Mockito
*/
class UserServiceTest {

    // Fake object of UserRepository
    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        // Initializes all the mock objects in this test class
        MockitoAnnotations.openMocks(this)

        Mockito.`when`(
            userRepository.loginUser(anyString(), anyString())
        ).thenReturn(LOGIN_STATUS.INVALID_PASSWORD)
    }

    @Test
    fun testUserService() {
        val userService = UserService(userRepository)
        val status = userService.loginUser("abc@gmail.com", "111")

        assertEquals("Invalid Password", status)

    }

}