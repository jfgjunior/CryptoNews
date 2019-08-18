package com.jfapps.cryptonews.viewmodel

import androidx.lifecycle.ViewModel
import com.jfapps.cryptonews.model.News
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class DetailsViewModel @AssistedInject constructor(
    @Assisted private val news: News
) : ViewModel() {
    val imageUrl: String?
        get() = news.urlToImage
    val title: String
        get() = news.title
    val content: String
        get() = news.content
    val newsUrl: String
        get() = news.url

    @AssistedInject.Factory
    interface Factory {
        fun create(news: News): DetailsViewModel
    }
}