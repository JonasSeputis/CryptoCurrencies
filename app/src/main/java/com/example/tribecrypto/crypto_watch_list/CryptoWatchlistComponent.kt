package com.example.tribecrypto.crypto_watch_list

import com.example.tribecrypto.di.component.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class])
interface CryptoWatchlistComponent {
    fun inject(cryptoWatchlistFragment: CryptoWatchlistFragment)
}