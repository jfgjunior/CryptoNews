package com.jfapps.cryptonews.network

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.jfapps.cryptonews.model.News
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.min

class NewsRepository @Inject constructor() : PageKeyedDataSource<Int, News>(), CoroutineScope {
    companion object {
        //To non paying users we have a limit of 100 news. Each request
        //gets 20 news, so in this case maxPages = 100/20 = 5
        private const val MAX_PAGES = 5
        private const val NEWS_PER_PAGE = 20f
    }

    private val job = Job()
    private var totalPages = 0
    override val coroutineContext = job + Dispatchers.Main

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, News>
    ) {

        launch(IO) {
            val result = newsApi.getCryptoNewsAsync(1)
            totalPages = maxPage(result.totalResults)
            callback.onResult(result.articles, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        launch(IO) {
            if (params.key <= totalPages) {
                val result = newsApi.getCryptoNewsAsync(params.key)
                totalPages = maxPage(result.totalResults)
                callback.onResult(result.articles, params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {}

    private val newsApi = NewsApiClient()
    private fun maxPage(totalResults: Int): Int =
        min(ceil(totalResults / NEWS_PER_PAGE).toInt(), MAX_PAGES)

    fun finalize() {
        cancel()
    }
}