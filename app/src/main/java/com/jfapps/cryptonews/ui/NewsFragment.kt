package com.jfapps.cryptonews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jfapps.cryptonews.R
import com.jfapps.cryptonews.databinding.FragmentNewsBinding
import com.jfapps.cryptonews.extensions.inject
import com.jfapps.cryptonews.extensions.viewModel
import com.jfapps.cryptonews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.news_list as newsList

class NewsFragment : Fragment() {
    private val viewModel: NewsViewModel by viewModel {
        inject.newsViewModelFactory.create(
            inject.newsRepository
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
        viewModel.fetchNews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsAdapter()
        newsList.adapter = adapter
        viewModel.news.observe(this,
            Observer {
                (newsList.adapter as NewsAdapter).submitList(it)
            })
    }
}