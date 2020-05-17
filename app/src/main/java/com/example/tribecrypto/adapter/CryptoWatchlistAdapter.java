package com.example.tribecrypto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tribecrypto.R;
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CryptoWatchlistAdapter extends RecyclerView.Adapter<CryptoWatchlistAdapter.WatchlistViewHolder> {

    private Context context;
    private List<CryptoCurrencyEntity> watchlist = new ArrayList<>();

    public CryptoWatchlistAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<CryptoCurrencyEntity> currencyList) {
        this.watchlist = currencyList;
    }

    @NonNull
    @Override
    public WatchlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false);
        return new WatchlistViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistViewHolder holder, int position) {
        holder.bind(watchlist.get(position), context);
    }

    @Override
    public int getItemCount() {
        return watchlist.size();
    }

    static class WatchlistViewHolder extends RecyclerView.ViewHolder {

        private TextView currencyName;
        private TextView percent;
        private ImageView arrowImageView;
        private ImageView currencyImageView;

        WatchlistViewHolder(View view) {
            super(view);
        }

        void bind(CryptoCurrencyEntity currency, Context context) {
            init(itemView);

            currencyName.setText(currency.getName());
            BigDecimal percentChange = new BigDecimal(currency.getPercentChange24h()).setScale(0, RoundingMode.UP);
            percent.setText(String.format("%s %%", percentChange));
            int drawableId;
            if (percentChange.compareTo(BigDecimal.ZERO) >= 0) {
                drawableId = R.drawable.ic_arrow_upward_green_24dp;
            } else {
                drawableId = R.drawable.ic_arrow_downward_red_24dp;
            }
            arrowImageView.setImageDrawable(context.getDrawable(drawableId));
            currencyImageView.setImageDrawable(context.getDrawable(R.drawable.circular_star_image));
        }

        private void init(View view) {
            currencyName = view.findViewById(R.id.viewTextViewCurrency);
            percent = view.findViewById(R.id.viewPercentValueTextView);
            arrowImageView = view.findViewById(R.id.viewArrowImageView);
            currencyImageView = view.findViewById(R.id.viewCurrencyImageView);
        }
    }
}
