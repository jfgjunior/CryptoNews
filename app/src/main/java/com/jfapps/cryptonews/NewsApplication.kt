package com.jfapps.cryptonews

import android.app.Application

class NewsApplication : Application(), Injector {
    override val inject: ApplicationComponent by lazy { DaggerApplicationComponent.create() }
}

interface Injector {
    val inject: ApplicationComponent
}