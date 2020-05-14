package com.example.tribecrypto.crypto_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tribecrypto.ApplicationClass
import com.example.tribecrypto.R
import com.example.tribecrypto.adapter.CryptoCurrenciesListAdapter
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.entity.WatchListEntity
import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject
import com.example.tribecrypto.utils.isConnectedToInternet
import kotlinx.android.synthetic.main.fragment_crypto_list_layout.view.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import javax.inject.Inject

class CryptoListFragment : Fragment(), CryptoListView,
    CryptoCurrenciesListAdapter.CurrencyViewHolder.OnItemClick {

    @Inject
    lateinit var presenter: CryptoListPresenter

    private lateinit var currencyAdapter: CryptoCurrenciesListAdapter

    override fun onAttach(context: Context) {
        DaggerCryptoListComponent.builder()
            .applicationComponent(ApplicationClass.getApplicationComponent(activity as Context))
            .build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_crypto_list_layout, container, false)
        currencyAdapter = CryptoCurrenciesListAdapter(activity as Context, this)

        root.viewToolbar.viewToolbarTextView.text = getString(R.string.crypto_test_app)
        root.viewRecyclerView.layoutManager = LinearLayoutManager(activity as Context)
        root.viewRecyclerView.adapter = currencyAdapter

        return root
    }

    override fun setEntityList(data: CryptoCurrencyAndWatchlistObject) {
        currencyAdapter.setList(data)
        currencyAdapter.notifyDataSetChanged()
    }

    override fun noInternetConnectionInformation() {
        Toast.makeText(activity as Context, getString(R.string.there_is_no_internet_connection), Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.getCurrenciesList(isConnectedToInternet(activity as Context))
    }

    override fun onPause() {
        presenter.setView(null)
        super.onPause()
    }

    override fun onStop() {
        presenter.setView(null)
        super.onStop()
    }

    override fun itemClicked(currency: CryptoCurrencyEntity, watchList: List<WatchListEntity>) {
        presenter.addCurrencyToWatchList(currency, watchList)
    }
}