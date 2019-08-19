package com.jfapps.cryptonews.bindingadapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter("app:loadUrl")
fun ImageView.loadUrl(url: String?) {
    if (url != null && url.isNotEmpty()) {
        Picasso.get()
            .load(url)
            .into(this, object : Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    visibility = View.GONE
                }

            })
    } else {
        visibility = View.GONE
    }
}
