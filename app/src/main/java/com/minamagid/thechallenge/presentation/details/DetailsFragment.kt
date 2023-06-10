package com.minamagid.thechallenge.presentation.details

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.minamagid.thechallenge.R
import com.minamagid.thechallenge.common.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article_details.view.*
import com.minamagid.thechallenge.domain.model.homeResponses.Result

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModels()
    private var completeUrl: String? = null
    val TAG ="mina_article_details"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_article_details, container, false)
        myListener(root)
        return root
    }


    @SuppressLint("WebViewApiAvailability", "SetJavaScriptEnabled")
    private fun myListener(binding: View) {

        // receive bundle here
        val bundle = arguments?.getString("article")?:""
        completeUrl = Constants.URL.plus(bundle)

        // webView with url has param
        binding.web_view.apply {
            settings.javaScriptEnabled = true
            loadUrl(completeUrl!!)
            webViewClient = object : WebViewClient() {
                @SuppressLint("WebViewClientOnReceivedSslError")
                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    handler?.proceed() // Accept all SSL certificates
                }
            }
        }
    }


}
