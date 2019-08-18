package com.jfapps.cryptonews

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AssistedInjectModule::class])
interface ApplicationComponent {

}