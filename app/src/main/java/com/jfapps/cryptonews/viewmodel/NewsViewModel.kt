package com.jfapps.cryptonews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfapps.cryptonews.model.News
import com.jfapps.cryptonews.network.NewsRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NewsViewModel @AssistedInject constructor(
    @Assisted private val newsRepository: NewsRepository
): ViewModel() {
    val news: LiveData<List<News>>
        get() = newsRepository.news

    fun fetchNews() {
        viewModelScope.launch(IO) {
            newsRepository.fetchNews()
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(newsRepository: NewsRepository): NewsViewModel
    }
}