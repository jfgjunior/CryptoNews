package com.jfapps.cryptonews.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jfapps.cryptonews.R
import com.jfapps.cryptonews.databinding.FragmentNewsBinding
import com.jfapps.cryptonews.extensions.inject
import com.jfapps.cryptonews.extensions.viewModel
import com.jfapps.cryptonews.model.News
import com.jfapps.cryptonews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.news_list as newsList

class NewsFragment : Fragment() {
    private val viewModel: NewsViewModel by viewModel {
        inject.newsViewModelFactory.create(
            inject.newsDataSourceFactory
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentNewsBinding>(
            inflater, R.layout.fragment_news, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsAdapter(::readNews, ::stopProgress)
        newsList.adapter = adapter
        viewModel.news.observe(this,
            Observer {
                (newsList.adapter as NewsAdapter).submitList(it)
            })

        credit.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(NewsViewModel.CREDIT_URL)
            startActivity(intent)
        }
    }

    private fun readNews(news: News) {
        val directions = NewsFragmentDirections
            .actionNewsFragmentToDetailsFragment(news)
        findNavController().navigate(directions)
    }

    private fun stopProgress(size: Int) {
        viewModel.handleProgress(size)
    }
}