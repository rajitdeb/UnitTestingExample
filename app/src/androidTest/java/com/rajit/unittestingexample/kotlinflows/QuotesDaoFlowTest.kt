package com.rajit.unittestingexample.kotlinflows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.rajit.unittestingexample.dao.QuotesDao
import com.rajit.unittestingexample.db.QuoteDatabase
import com.rajit.unittestingexample.entity.Quote
import com.rajit.unittestingexample.getOrAwaitValue
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// This is an Instrumentation Test because we needed context in here
class QuotesDaoFlowTest {

    // In test environment, we always test our test cases in synchronous mode
    // and avoid Background Thread Processing
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var quoteDatabase: QuoteDatabase
    private lateinit var quotesDao: QuotesDao

    @Before
    fun setup() {
        // Here, we use allowMainThreadQueries()
        // because it will be running through the test case we can use main thread
        quoteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()

        quotesDao = quoteDatabase.quoteDao()
    }

    @Test
    fun insertQuote_expectedSingleQuote() = runBlocking {

        val quote = Quote(0, "This is a test quote", "Cheezy Code")
        quotesDao.insertQuote(quote)

        val result = quotesDao.getAllQuotesViaFlow().first()
        TestCase.assertEquals(1, result.size)
        // InstantTaskExecutorRule -> A Junit Test rule that synchronously executes the tasks
        TestCase.assertEquals("This is a test quote", result[0].quote)

    }

    @Test
    fun insertQuote_expectedSingleQuote_viaTurbineTest() = runBlocking {

        val quote = Quote(0, "This is a test quote", "Cheezy Code")
        quotesDao.insertQuote(quote)

        val result = quotesDao.getAllQuotesViaFlow().test {
            val quoteList = awaitItem()
            assertEquals(1, quoteList.size)

            // cancels the flow
            cancel()
        }


    }

    @Test
    fun insertQuote_expectedTwoQuote_viaTurbineTest() = runBlocking {

        val quote = Quote(0, "This is a test quote", "Cheezy Code")
        val quote2 = Quote(0, "This is a test quote 2", "Rajit Deb")
        quotesDao.insertQuote(quote)

        launch {
            delay(500)
            quotesDao.insertQuote(quote2)
        }

        val result = quotesDao.getAllQuotesViaFlow().test {
            val quoteList = awaitItem()
            assertEquals(1, quoteList.size)

            val quoteList2 = awaitItem()
            assertEquals(2, quoteList2.size)

            // cancels the flow
            cancel()
        }


    }

    @After
    fun tearDown() {
        quoteDatabase.close()
    }


}