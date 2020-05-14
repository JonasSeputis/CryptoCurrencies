package com.example.tribecrypto.crypto_watch_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tribecrypto.ApplicationClass
import com.example.tribecrypto.R
import com.example.tribecrypto.adapter.CryptoWatchlistAdapter
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import kotlinx.android.synthetic.main.fragment_crypto_list_layout.view.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import javax.inject.Inject

class CryptoWatchlistFragment : Fragment(), CryptoWatchlistView {

    @Inject
    lateinit var presenter: CryptoWatchlistPresenter

    private lateinit var adapter: CryptoWatchlistAdapter

    override fun onAttach(context: Context) {
        DaggerCryptoWatchlistComponent.builder()
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
        adapter = CryptoWatchlistAdapter(activity as Context)
        root.viewToolbar.viewToolbarTextView.text = getString(R.string.watchlist)
        root.viewRecyclerView.layoutManager = LinearLayoutManager(activity as Context)
        root.viewRecyclerView.adapter = adapter

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadInfo()
    }

    override fun onPause() {
        presenter.setView(null)
        super.onPause()
    }

    override fun onStop() {
        presenter.setView(null)
        super.onStop()
    }

    override fun provideCurrencyList(data: List<CryptoCurrencyEntity>) {
        adapter.setList(data)
        adapter.notifyDataSetChanged()
    }
}