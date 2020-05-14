package com.example.tribecrypto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tribecrypto.R
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.entity.WatchListEntity
import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject
import kotlinx.android.synthetic.main.currency_item_layout.view.*
import java.math.BigDecimal
import java.math.RoundingMode

class CryptoCurrenciesListAdapter(
    private val context: Context,
    private val onItemClick: CurrencyViewHolder.OnItemClick
) : RecyclerView.Adapter<CryptoCurrenciesListAdapter.CurrencyViewHolder>() {

    var listsObject = CryptoCurrencyAndWatchlistObject(emptyList(), emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflatedView =
            LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false)
        return CurrencyViewHolder(inflatedView)
    }

    fun setList(currencyList: CryptoCurrencyAndWatchlistObject) {
        this.listsObject = currencyList
    }

    override fun getItemCount() = listsObject.currency.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(
            listsObject.currency[position],
            listsObject.watchlist,
            context,
            onItemClick
        )
    }

    class CurrencyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            currency: CryptoCurrencyEntity,
            watchList: List<WatchListEntity>,
            context: Context,
            onItemClick: OnItemClick
        ) {
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
                    getCurrencyImage(
                        currency,
                        watchList
                    )
                )
            )

            view.viewBaseLayout.setOnLongClickListener {
                val drawable = getCurrencyImage(currency, watchList)
                view.viewCurrencyImageView.setImageDrawable(context.getDrawable(drawable))
                onItemClick.itemClicked(currency, watchList)
                true
            }
        }

        private fun getCurrencyImage(
            currency: CryptoCurrencyEntity,
            watchList: List<WatchListEntity>
        ): Int {
            var imageId = R.drawable.circular_image_background
            for (watch in watchList) {
                if (watch.name == currency.name) {
                    imageId = R.drawable.circular_star_image
                }
            }
            return imageId
        }

        interface OnItemClick {
            fun itemClicked(currency: CryptoCurrencyEntity, watchList: List<WatchListEntity>)
        }
    }
}