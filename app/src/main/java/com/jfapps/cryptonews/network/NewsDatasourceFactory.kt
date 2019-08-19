package com.jfapps.cryptonews.network

import androidx.paging.DataSource
import com.jfapps.cryptonews.model.News
import javax.inject.Inject

class NewsDataSourceFactory @Inject constructor(private val newsRepository: NewsRepository) :
    DataSource.Factory<Int, News>() {
    override fun create(): DataSource<Int, News> {
        return newsRepository
    }

    fun finalize() {
        newsRepository.finalize()
    }
}