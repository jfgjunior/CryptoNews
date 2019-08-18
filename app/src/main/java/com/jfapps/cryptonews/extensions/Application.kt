package com.jfapps.cryptonews.extensions

import android.app.Application
import com.jfapps.cryptonews.ApplicationComponent
import com.jfapps.cryptonews.Injector

val Application.inject: ApplicationComponent
    get() = (this as Injector).inject