package com.rajit.unittestingexample

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import com.rajit.unittestingexample.util.QuoteManager
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class QuoteManagerTest {

    private lateinit var quoteManager: QuoteManager

    @Before
    fun setup() {
        quoteManager = QuoteManager()
    }

    @Test(expected = FileNotFoundException::class)
    fun testPopulateQuoteFromAssets_inputContext_EmptyFileName_Expected_FileNotFoundException() {
        // get application context
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuotesFromAssets(context, "")
    }

    @Test()
    fun testPopulateQuoteFromAssets_inputContext_ProperFileName_ExpectedTestPass() {
        // get application context
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuotesFromAssets(context, "quote.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun testPopulateQuoteFromAssets_invalidJSONException() {
        // get application context
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuotesFromAssets(context, "malformed.json")
    }

    @Test
    fun testPopulateQuoteFromAssets_validJSON_expectedCount() {
        // get application context
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuotesFromAssets(context, "quote.json")
        assertEquals(2, quoteManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuotesFromAssets(context, "quote.json")

        val quote = quoteManager.getPreviousQuote()
        assertEquals("Yogi Berra", quote.author)
    }

    @Test
    fun testNextQuote() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuotesFromAssets(context, "quote.json")

        val quote = quoteManager.getNextQuote()
        assertEquals("Yogi Berra", quote.author)
    }

    @Test
    fun testNextQuote_inputDoubleNext_ExpectedFirstValue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuotesFromAssets(context, "quote.json")

        var quote = quoteManager.getNextQuote()
        quote = quoteManager.getNextQuote()
        assertEquals("Thomas Edison", quote.author)
    }

}