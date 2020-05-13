package com.example.tribecrypto.cryptoList

import com.example.tribecrypto.di.component.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class])
interface CryptoListComponent {
    fun inject(cryptoListFragment: CryptoListFragment)
}