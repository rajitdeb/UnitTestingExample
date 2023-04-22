package com.rajit.unittestingexample

import com.rajit.unittestingexample.util.CoroutineUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
*   NOTE:
*   Whenever we try to execute a code which is inside a coroutine scope
*   That coroutine scope never gets executed on its own in the test environment
*   TO ENSURE SINGLE THREAD EXECUTION
*   It's the responsibility of the developer to test them, if needed
*   And all the coroutines are scheduled in a queue
*   Therefore, we use the following line to execute the code block inside the coroutines:
*   mainCoroutinesRule.testDispatcher.scheduler.advanceUntilIdle()
*
*   Here,
*   mainCoroutinesRule = The rule where all the common actions related to the setup() and tearDown() are written
*   testDispatcher = The testDispatcher to use for the test cases
*   scheduler = where the coroutines are actually queued
*   advanceUntilIdle() = block the thread until all the coroutines are executed
*/
class CoroutineUtilTest {

//    private lateinit var cUtil: CoroutineUtil

    // `StandardTestDispatcher` makes sure that our test cases are running on only single thread
    // So that the test cases can be tested in isolation
//    private val testDispatcher = StandardTestDispatcher()

    /*
    * We used this rule to make the code in mainCoroutineRule class reusable
    * Basically whenever we need a testDispatcher in any test file we will simply use it
    * from this rule, without additional code
    */
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

//    @Test
//    fun testGetUsername() {
//        // For this scenario, `runBlocking{}` is not a good way to test coroutines
//        runBlocking {
//            cUtil.getUsername()
//        }
//    }

    @Test
    fun testGetUsername() {

        val cUtil = CoroutineUtil(mainCoroutineRule.testDispatcher)

        /*
        * Here, `runTest{}` is given by the kotlinx test dependency
        * which bypasses delays() and directly executes the following commands
        * in the test environment
        */
        runTest {

            cUtil.getUsername()
        }
    }


    @Test
    fun testGetUser() {

        val cUtil = CoroutineUtil(mainCoroutineRule.testDispatcher)

        /*

        * Here, in our main CoroutineUtil Class we have used a Main Dispatcher
        * Which basically runs the code in the Main Thread
        * But in the test environment, there is no Main Dispatcher
        * So it will give error, so we add the following line:
        *
        * 1. We set the test Dispatcher
        * 2. We use it as Main Dispatcher before running every test case
        * 3. We reset the Main Dispatcher after running every test case

        */

        runTest {
            cUtil.getUser()
        }

    }

    @Test
    fun testGetAddress() {

        val cUtil = CoroutineUtil(mainCoroutineRule.testDispatcher)

        /*
        * Here, since in our original CoroutineUtil class we have used Dispatchers.IO
        * There is no such dispatcher for the IO Dispatcher
        * And also it is a good practice to hard code Dispatchers
        * Therefore, Google recommends injecting the dispatcher in the class itself
        * So, we used a dispatcher arg to pass the same to the getAddress() in `CoroutineUtil` class
        */

        runTest {
            cUtil.getAddress()
        }
    }

//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }

}