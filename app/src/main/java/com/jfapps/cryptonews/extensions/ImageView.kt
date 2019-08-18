package com.jfapps.cryptonews.extensions

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadUrl(url: String?) {
    if(url != null) {
        Picasso.get()
            .load(url)
            .into(this, object: Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    visibility = View.GONE
                }

            })
    } else {
        visibility = View.GONE
    }
}