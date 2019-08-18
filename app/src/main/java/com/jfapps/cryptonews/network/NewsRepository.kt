package com.jfapps.cryptonews.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jfapps.cryptonews.model.News
import javax.inject.Inject

class NewsRepository @Inject constructor(){
    private val newsApi = NewsApiClient()
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>>
        get() = _news

    suspend fun fetchNews() {
        _news.postValue(newsApi.getCryptoNewsAsync(1).articles)
    }
}