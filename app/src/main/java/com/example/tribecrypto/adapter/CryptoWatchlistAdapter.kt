package com.example.tribecrypto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tribecrypto.R
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import kotlinx.android.synthetic.main.currency_item_layout.view.*
import java.math.BigDecimal
import java.math.RoundingMode

class CryptoWatchlistAdapter(
    private val context: Context
) : RecyclerView.Adapter<CryptoWatchlistAdapter.WatchlistViewHolder>() {

    private var watchlist: List<CryptoCurrencyEntity> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WatchlistViewHolder {
        val inflatedView =
            LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false)
        return WatchlistViewHolder(inflatedView)
    }

    fun setList(currencyList: List<CryptoCurrencyEntity>) {
        this.watchlist = currencyList
    }

    override fun getItemCount() = watchlist.size

    override fun onBindViewHolder(
        holder: WatchlistViewHolder,
        position: Int
    ) {
        holder.bind(watchlist[position], context)
    }

    class WatchlistViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(currency: CryptoCurrencyEntity, context: Context) {
            view.viewTextViewCurrency.text = currency.name
            val percentChange = BigDecimal(currency.percentChange24h).setScale(0, RoundingMode.UP)
            view.viewPercentValueTextView.text = String.format("%s %%", percentChange)
            val drawableId = if (percentChange > BigDecimal.ZERO) {
                R.drawable.ic_arrow_upward_green_24dp
            } else {
                R.drawable.ic_arrow_downward_red_24dp
            }
            view.viewArrowImageView.setImageDrawable(context.getDrawable(drawableId))
            view.viewCurrencyImageView.setImageDrawable(
                context.getDrawable(
                    R.drawable.circular_star_image
                )
            )
        }
    }
}