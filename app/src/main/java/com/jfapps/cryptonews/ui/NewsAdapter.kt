package com.jfapps.cryptonews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jfapps.cryptonews.R
import com.jfapps.cryptonews.extensions.loadUrl
import com.jfapps.cryptonews.extensions.toWritten
import com.jfapps.cryptonews.model.News
import kotlinx.android.synthetic.main.item_news.view.*
import java.util.*

class NewsAdapter: ListAdapter<News, NewsAdapter.NewsViewHolder>(newsDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            itemView.title.text = news.title
            itemView.preview.text = news.content
            itemView.image.loadUrl(news.urlToImage)
            itemView.date.text = Calendar.getInstance().toWritten(news.publishedAt)
        }
    }

    companion object {
        private val newsDiffCallback = object: DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: News, newItem: News) =
                oldItem == newItem
        }
    }
}