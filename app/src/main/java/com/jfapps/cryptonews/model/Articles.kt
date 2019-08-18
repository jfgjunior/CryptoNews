package com.jfapps.cryptonews.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Articles(
    val articles: List<News>,
    val status: String,
    val totalResults: Int
)

@Parcelize
data class News(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?
) : Parcelable