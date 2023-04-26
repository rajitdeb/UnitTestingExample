package com.rajit.unittestingexample.api

import com.rajit.unittestingexample.util.JHelper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var productAPI: ProductAPI

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        productAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductAPI::class.java)
    }

    @Test
    fun test_getProducts_expectedEmptyList() = runTest {

        // Creating a response to be sent by MockWebServer
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        // Storing the response received from the server
        val response = productAPI.getProducts()

        // Start accepting requests that are incoming to the MockWebServer
        mockWebServer.takeRequest()

        assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun test_getProducts_expectedProducts() = runTest {

        // Creating a response to be sent by MockWebServer
        val mockResponse = MockResponse()
        val jsonContent = JHelper.readFileResource("/rito.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(jsonContent)
        mockWebServer.enqueue(mockResponse)

        // Storing the response received from the server
        val response = productAPI.getProducts()

        // Start accepting requests that are incoming to the MockWebServer
        mockWebServer.takeRequest()

        assertEquals(2, response.body()!!.size)
        assertEquals(false, response.body()!!.isEmpty())

    }

    @Test
    fun test_getProducts_expectedReturnError() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went Wrong")
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()

        assertEquals(false, response.isSuccessful)
        assertEquals(404, response.code())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}