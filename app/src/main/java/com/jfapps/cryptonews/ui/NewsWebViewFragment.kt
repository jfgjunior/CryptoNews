package com.jfapps.cryptonews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jfapps.cryptonews.R
import com.jfapps.cryptonews.databinding.FragmentNewsWebviewBinding
import com.jfapps.cryptonews.extensions.inject
import com.jfapps.cryptonews.extensions.viewModel
import com.jfapps.cryptonews.viewmodel.WebViewModel
import kotlinx.android.synthetic.main.fragment_news_webview.*

class NewsWebViewFragment : Fragment() {
    private val viewModel: WebViewModel by viewModel {
        inject.webViewModelFactory.create(
            NewsWebViewFragmentArgs.fromBundle(arguments!!).url
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentNewsWebviewBinding>(
            inflater, R.layout.fragment_news_webview, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webview.settings.loadsImagesAutomatically = true
        webview.settings.javaScriptEnabled = true
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                viewModel.handleProgress(newProgress)
            }
        }
        webview.loadUrl(viewModel.url)
    }
}