package com.rajit.unittestingexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rajit.unittestingexample.repository.ProductRepository

class MainViewModelFactory(
    private val productRepository: ProductRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(productRepository) as T
    }

}