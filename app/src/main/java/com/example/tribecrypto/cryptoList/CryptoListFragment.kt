package com.example.tribecrypto.cryptoList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tribecrypto.ApplicationClass
import com.example.tribecrypto.R
import com.example.tribecrypto.data.CryptoCurrencyEntity
import kotlinx.android.synthetic.main.fragment_crypto_list_layout.*
import kotlinx.android.synthetic.main.fragment_crypto_list_layout.view.*
import timber.log.Timber
import javax.inject.Inject

class CryptoListFragment : Fragment(), CryptoListView, View.OnClickListener {

    @Inject
    lateinit var presenter: CryptoListPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_crypto_list_layout, container, false)

        DaggerCryptoListComponent.builder()
            .applicationComponent(ApplicationClass.getApplicationComponent(activity as Context))
            .build().inject(this)

        presenter.setView(this)
        presenter.test()

        root.viewButton.setOnClickListener(this)

        return root
    }

    override fun countOfCurrenciesReceived(value: Int) {
        Timber.d("CryptoListFragment: $value")
    }

    override fun setList(data: List<CryptoCurrencyEntity>) {
        Timber.d("GOT DATA ${data.size}")
        data.forEach { it -> Timber.d(it.name) }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            viewButton.id -> {
                Timber.d("PRESSED")
                presenter.getAllItemsFromDatabase()
            }
        }
    }
}