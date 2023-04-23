package com.rajit.unittestingexample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajit.unittestingexample.StoreApplication
import com.rajit.unittestingexample.adapter.ProductAdapter
import com.rajit.unittestingexample.databinding.FragmentProductStoreBinding
import com.rajit.unittestingexample.util.NetworkResult
import com.rajit.unittestingexample.viewmodel.MainViewModel
import com.rajit.unittestingexample.viewmodel.MainViewModelFactory


class ProductStoreFragment : Fragment() {

    private var _binding: FragmentProductStoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductStoreBinding.inflate(inflater, container, false)

        val repository = (activity?.application as StoreApplication).productRepository


        binding.apply {

            productRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
            }

        }

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getProducts()
        mainViewModel.products.observe(viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Success -> {
                    adapter = ProductAdapter(it.data!!)
                    binding.productRecyclerView.adapter = adapter
                }
                is NetworkResult.Error -> { Log.e("RESULT ERROR", it.msg.toString()) }
                is NetworkResult.Loading -> {}
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}