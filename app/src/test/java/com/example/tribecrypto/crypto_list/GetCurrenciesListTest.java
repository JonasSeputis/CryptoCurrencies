package com.example.tribecrypto.crypto_list;

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject;
import com.example.tribecrypto.data.network_object.CryptoCurrencyObject;
import com.example.tribecrypto.repository.CurrencyDetailsRepository;
import com.example.tribecrypto.repository.CurrencyRepository;
import com.example.tribecrypto.repository.WatchListRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.Single;

public class GetCurrenciesListTest {

    @Mock
    CurrencyRepository currencyRepository;

    @Mock
    CurrencyDetailsRepository currencyDetailsRepository;

    @Mock
    WatchListRepository watchListRepository;

    @Mock
    CryptoListView view;


    private CryptoListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(new CryptoListPresenter(currencyRepository, currencyDetailsRepository, watchListRepository));
    }

    @Test
    public void connectedToInternet() {
        // Mock
        Mockito.doReturn(Single.fromObservable(Observable.<CryptoCurrencyCallObject>empty())).when(currencyRepository).getCurrencyListFromApi();
        // Act
        presenter.getCurrenciesList(true);
        // Assert
        Mockito.verify(presenter, Mockito.atLeastOnce()).getCurrenciesFromApi();
    }

    @Test
    public void notConnectedToInternet() {
        // Mock
        Mockito.doReturn(view).when(presenter).getView();
        Mockito.doReturn(Observable.<CryptoCurrencyEntity>empty()).when(currencyRepository).getAllCurrencies();
        // Act
        presenter.getCurrenciesList(false);
        // Assert
        Mockito.verify(view, Mockito.times(1)).noInternetConnectionInformation();
        Mockito.verify(presenter, Mockito.times(1)).getCurrenciesFromDB();
    }
}