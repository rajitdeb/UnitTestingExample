package com.rajit.unittestingexample.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rajit.unittestingexample.MainActivity
import com.rajit.unittestingexample.R
import com.rajit.unittestingexample.databinding.FragmentQuoteBinding
import com.rajit.unittestingexample.model.Quote
import com.rajit.unittestingexample.util.QuoteManager

class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    private val quoteManager: QuoteManager = QuoteManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)

        quoteManager.populateQuotesFromAssets(requireContext(), "quote.json")

        binding.apply {

            productStoreBtn.setOnClickListener {
                findNavController().navigate(R.id.action_quoteFragment_to_productStoreFragment)
            }

            quoteTxt.text = quoteManager.getCurrentQuote().quote
            quoteAuthor.text = quoteManager.getCurrentQuote().author

            shareButton.setOnClickListener {

                val currentQuote = quoteManager.getCurrentQuote()

                try {

                    val intent = Intent().apply{
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "${currentQuote.quote} \n\n-${currentQuote.author}")
                        type="text/plain"
                    }

                    val shareIntent = Intent.createChooser(intent, null)
                    startActivity(shareIntent)

                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        context,
                        "No apps found to handle this operation",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            nextBtn.setOnClickListener {
                quoteManager.getNextQuote()
                updateQuoteView()
            }

            prevBtn.setOnClickListener {
                quoteManager.getPreviousQuote()
                updateQuoteView()
            }

        }

        return binding.root
    }

    private fun updateQuoteView(): Unit {

        binding.apply {
            quoteTxt.text = quoteManager.getCurrentQuote().quote
            quoteAuthor.text = quoteManager.getCurrentQuote().author
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}