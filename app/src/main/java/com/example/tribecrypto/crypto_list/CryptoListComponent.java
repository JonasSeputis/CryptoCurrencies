package com.example.tribecrypto.crypto_list;


import com.example.tribecrypto.di.component.ApplicationComponent;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
public interface CryptoListComponent {
    void inject(CryptoListFragment cryptoListFragment);
}
