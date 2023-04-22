package com.rajit.unittestingexample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainCoroutineRule: TestWatcher() {

    // TestDispatcher for mocking Dispatchers.IO & Dispatchers.MAIN, etc.
    val testDispatcher = StandardTestDispatcher()

    override fun starting(description: Description) {
        super.starting(description)
        // All code that is to be put in @Before block is written here
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        // All code that is to be put in @After block is written here
        Dispatchers.resetMain()
    }
}