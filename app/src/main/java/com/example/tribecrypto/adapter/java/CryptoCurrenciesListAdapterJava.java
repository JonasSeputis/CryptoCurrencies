package com.example.tribecrypto.adapter.java;

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
import com.example.tribecrypto.data.entity.WatchListEntity;
import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CryptoCurrenciesListAdapterJava extends RecyclerView.Adapter<CryptoCurrenciesListAdapterJava.CurrencyViewHolder> {

    private CryptoCurrencyAndWatchlistObject listsObject = new CryptoCurrencyAndWatchlistObject(new ArrayList<>(), new ArrayList<>());
    private Context context;
    private CurrencyViewHolder.OnItemClickJava onItemClickJava;

    public CryptoCurrenciesListAdapterJava(Context context,
                                           CurrencyViewHolder.OnItemClickJava onItemClickJava) {
        super();
        this.context = context;
        this.onItemClickJava = onItemClickJava;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.bind(listsObject.getCurrency().get(position),
                listsObject.getWatchlist(),
                context,
                onItemClickJava);
    }

    public void setList(CryptoCurrencyAndWatchlistObject  list) {
        this.listsObject = list;
    }

    @Override
    public int getItemCount() {
        return listsObject.getCurrency().size();
    }


    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {

        private TextView currencyTextView;
        private TextView percentValueView;
        private ImageView arrowImageView;
        private ImageView currencyImageView;
        private View baseLayout;

        CurrencyViewHolder(View view) {
            super(view);
        }

        private void bind(CryptoCurrencyEntity currency,
                          List<WatchListEntity> watchList,
                          Context context,
                          OnItemClickJava onItemClick) {

            init(itemView);

            currencyTextView.setText(currency.getName());
            BigDecimal percentValue = new BigDecimal(currency.getPercentChange24h()).setScale(0, BigDecimal.ROUND_UP);
            percentValueView.setText(String.format("%s %%", percentValue));
            int drawableId;
            if(percentValue.compareTo(BigDecimal.ZERO) >= 0) {
                drawableId = R.drawable.ic_arrow_upward_green_24dp;
            } else {
                drawableId = R.drawable.ic_arrow_downward_red_24dp;
            }
            arrowImageView.setImageDrawable(context.getDrawable(drawableId));
            currencyImageView.setImageDrawable(context.getDrawable(getCurrencyImage(currency, watchList)));

            baseLayout.setOnLongClickListener(v -> {
                    int drawable = getCurrencyImage(currency, watchList);
                    currencyImageView.setImageDrawable(context.getDrawable(drawable));
                    onItemClick.itemClicked(currency, watchList);
                    return true;
        });
        }

        private int getCurrencyImage(CryptoCurrencyEntity currencyEntity, List<WatchListEntity> watchList) {
            int imageId = R.drawable.circular_image_background;
            for(WatchListEntity watch : watchList) {
                if(watch.getName().equals(currencyEntity.getName())) {
                    imageId = R.drawable.circular_star_image;
                }
            }
            return imageId;
        }

        private void init(View itemView) {
            currencyTextView = itemView.findViewById(R.id.viewTextViewCurrency);
            percentValueView = itemView.findViewById(R.id.viewPercentValueTextView);
            arrowImageView = itemView.findViewById(R.id.viewArrowImageView);
            currencyImageView = itemView.findViewById(R.id.viewCurrencyImageView);
            baseLayout = itemView.findViewById(R.id.viewBaseLayout);
        }


        public interface OnItemClickJava {
            void itemClicked(CryptoCurrencyEntity currency, List<WatchListEntity> watchlist);
        }
    }
}
