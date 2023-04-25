package com.rajit.unittestingexample.repository

import com.rajit.unittestingexample.api.ProductAPI
import com.rajit.unittestingexample.model.ProductListItem
import com.rajit.unittestingexample.util.NetworkResult

class ProductRepository(
    private val productAPI: ProductAPI
) {
    suspend fun getProducts(): NetworkResult<List<ProductListItem>> {

        val response = productAPI.getProducts()

        return if (response.isSuccessful) {
            val responseBody = response.body()
            if(responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error("Something went Wrong")
            }
        } else {
            NetworkResult.Error("Something really went wrong")
        }

    }

}