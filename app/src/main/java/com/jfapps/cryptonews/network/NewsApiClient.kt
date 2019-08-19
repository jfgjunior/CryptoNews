package com.jfapps.cryptonews.network

import com.jfapps.cryptonews.BuildConfig
import com.jfapps.cryptonews.extensions.passedDays
import com.jfapps.cryptonews.extensions.today
import com.jfapps.cryptonews.model.Articles
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class NewsApiClient {
    companion object {
        private const val BASE_URL = "https://newsapi.org/"
        private const val API_KEY_PARAM = "apiKey"
        private const val Q_PARAM = "q"
        private const val SORT_BY_PARAM = "sortBy"
        private const val FROM_PARAM = "from"
        private const val TO_PARAM = "to"
        private const val PAGE_PARAM = "page"
        private const val Q = "cryptocurrency"
        private const val SORT_BY = "publishedAt"
        private const val MAX_DAYS_AGO = 20
    }

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor {
        val request = it.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter(API_KEY_PARAM, BuildConfig.apiKey)
            .addQueryParameter(Q_PARAM, Q)
            .addQueryParameter(SORT_BY_PARAM, SORT_BY)
            .addEncodedQueryParameter(FROM_PARAM, Calendar.getInstance().today)
            .addEncodedQueryParameter(TO_PARAM, Calendar.getInstance().passedDays(MAX_DAYS_AGO))
            .build()
        //TODO: Handle timeout
        it.proceed(request.newBuilder().url(url).build())
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsEndpoint::class.java)

    suspend fun getCryptoNewsAsync(page: Int): Articles {
        while (true) {
            runCatching {
                retrofit.getCryptoNewsAsync(page)
            }.onSuccess {
                return it
            }.onFailure {
                delay(300)
            }
        }
    }

    interface NewsEndpoint {
        @GET("v2/everything")
        suspend fun getCryptoNewsAsync(@Query(PAGE_PARAM) page: Int): Articles
    }
}