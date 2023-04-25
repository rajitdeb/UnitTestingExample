package com.rajit.unittestingexample.repository

import com.rajit.unittestingexample.api.ProductAPI
import com.rajit.unittestingexample.model.ProductListItem
import com.rajit.unittestingexample.model.Rating
import com.rajit.unittestingexample.util.NetworkResult
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {

    @Mock
    lateinit var productAPI: ProductAPI

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test_getProducts_EmptyList() = runTest {
        Mockito.`when`(productAPI.getProducts())
            .thenReturn(Response.success(emptyList()))

        val systemUnderTest = ProductRepository(productAPI)
        val result = systemUnderTest.getProducts()
        assertEquals(true, result is NetworkResult.Success)
        assertEquals(0, result.data!!.size)

    }

    @Test
    fun test_getProducts_expectedProductList() = runTest {

        val productList = listOf<ProductListItem>(
            ProductListItem(
                1,
                "Product 1",
                40.30,
                "This is a sample description",
                "Men Trousers",
                "",
                Rating(
                    3.6,
                    120
                )
            ),

            ProductListItem(
                2,
                "Product 2",
                35.60,
                "This is a sample description",
                "Men T-shirts",
                "",
                Rating(
                    3.9,
                    50
                )
            ),
        )

        Mockito.`when`(productAPI.getProducts())
            .thenReturn(Response.success(productList))

        val systemUnderTest = ProductRepository(productAPI)
        val result = systemUnderTest.getProducts()
        assertEquals(true, result is NetworkResult.Success)
        assertEquals(2, result.data!!.size)
        assertEquals("Product 1", result.data!![0].title)

    }

    @Test
    fun test_getProducts_expectedError() = runTest {

        Mockito.`when`(productAPI.getProducts())
            .thenReturn(Response.error(401, "Unauthorized".toResponseBody()))

        val systemUnderTest = ProductRepository(productAPI)
        val result = systemUnderTest.getProducts()

        assertEquals(true, result is NetworkResult.Error)
        assertEquals("Something really went wrong", result.msg)

    }

}