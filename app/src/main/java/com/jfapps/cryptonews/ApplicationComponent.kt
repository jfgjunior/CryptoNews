package com.jfapps.cryptonews

import com.jfapps.cryptonews.network.NewsDataSourceFactory
import com.jfapps.cryptonews.network.NewsRepository
import com.jfapps.cryptonews.viewmodel.DetailsViewModel
import com.jfapps.cryptonews.viewmodel.NewsViewModel
import com.jfapps.cryptonews.viewmodel.WebViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AssistedInjectModule::class])
interface ApplicationComponent {
    val newsRepository: NewsRepository
    val newsViewModelFactory: NewsViewModel.Factory
    val detailsViewModelFactory: DetailsViewModel.Factory
    val webViewModelFactory: WebViewModel.Factory
    val newsDataSourceFactory: NewsDataSourceFactory
}