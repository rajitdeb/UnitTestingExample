package com.rajit.unittestingexample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.rajit.unittestingexample.dao.QuotesDao
import com.rajit.unittestingexample.db.QuoteDatabase
import com.rajit.unittestingexample.entity.Quote
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
* For Testing Room Database in isolation, room provides a provision
* for IN-MEMORY DATABASE, where we can create a database for each test case
* and dump it after testing
*/

class QuotesDaoTest {

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

        val result = quotesDao.getAllQuotes().getOrAwaitValue() // to wait for the livedata to load in the main thread
        assertEquals(1, result.size)
        // InstantTaskExecutorRule -> A Junit Test rule that synchronously executes the tasks
        assertEquals("This is a test quote", result[0].quote)

    }

    @Test
    fun deleteQuote_expectedNoResults() = runBlocking {

        val quote = Quote(0, "This is a test quote", "Cheezy Code")
        quotesDao.insertQuote(quote)

        quotesDao.deleteAllQuotes()

        val result = quotesDao.getAllQuotes().getOrAwaitValue()
        assertEquals(0, result.size)

    }


    @After
    fun tearDown() {
        quoteDatabase.close()
    }


}