package com.example.tribecrypto.cryptoList

import dagger.Component

@Component
interface CryptoListComponent {
    fun inject(cryptoListFragment: CryptoListFragment)
}