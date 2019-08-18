package com.jfapps.cryptonews.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class WebViewModel @AssistedInject constructor(
    @Assisted val url: String
): ViewModel() {
    private val _progress = MutableLiveData<Int>().apply { value = View.VISIBLE }
    private val _progressVisibility = MutableLiveData<Int>()
    val progress: LiveData<Int>
        get() = _progress
    val progressVisibility: LiveData<Int>
        get() = _progressVisibility

    fun handleProgress(progress: Int) {
        _progress.value = progress
        if (progress == 100) {
            _progressVisibility.value = View.GONE
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(url: String): WebViewModel
    }
}