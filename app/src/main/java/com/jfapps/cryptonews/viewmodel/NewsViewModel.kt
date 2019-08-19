package com.jfapps.cryptonews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jfapps.cryptonews.model.News
import com.jfapps.cryptonews.network.NewsDataSourceFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class NewsViewModel @AssistedInject constructor(
    @Assisted private val newsDataSourceFactory: NewsDataSourceFactory
) : ViewModel() {
    companion object {
        const val CREDIT_URL = "https://newsapi.org/"
    }

    val news: LiveData<PagedList<News>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(1)
            .setEnablePlaceholders(true)
            .build()
        news = LivePagedListBuilder(newsDataSourceFactory, pagedListConfig).build()
    }

    override fun onCleared() {
        newsDataSourceFactory.finalize()
        super.onCleared()
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(newsDataSourceFactory: NewsDataSourceFactory): NewsViewModel
    }
}