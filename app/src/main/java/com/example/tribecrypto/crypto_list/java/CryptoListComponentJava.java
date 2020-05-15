package com.example.tribecrypto.crypto_list.java;

import com.example.tribecrypto.di.component.ApplicationComponent;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
public interface CryptoListComponentJava {
    void inject(CryptoListFragmentJava cryptoListFragment);
}
