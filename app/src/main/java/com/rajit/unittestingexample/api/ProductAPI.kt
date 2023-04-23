package com.rajit.unittestingexample.api

import com.rajit.unittestingexample.model.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts(): Response<List<ProductListItem>>

}