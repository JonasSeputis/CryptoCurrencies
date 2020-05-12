package com.example.tribecrypto.cryptoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tribecrypto.R
import timber.log.Timber
import javax.inject.Inject

class CryptoListFragment : Fragment(), CryptoListView {

    @Inject
    lateinit var presenter: CryptoListPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_crypto_list_layout, container, false)

        DaggerCryptoListComponent.builder().build().inject(this)
        presenter.setView(this)
        presenter.test()

        return root
    }

    override fun countOfCurrenciesReceived(value: Int) {
        Timber.d("CryptoListFragment: $value")
    }
}