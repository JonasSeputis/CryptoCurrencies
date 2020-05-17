package com.example.tribecrypto.crypto_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tribecrypto.R;
import com.example.tribecrypto.adapter.CryptoCurrenciesListAdapter;
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.entity.WatchListEntity;
import com.example.tribecrypto.java.ApplicationClass;
import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject;
import com.example.tribecrypto.utils.InternetConnectionJava;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

public class CryptoListFragment extends Fragment implements CryptoListView, CryptoCurrenciesListAdapter.CurrencyViewHolder.OnItemClickJava {

    @Inject
    CryptoListPresenter presenter;

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private CryptoCurrenciesListAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        DaggerCryptoListComponent.builder()
                .applicationComponent(ApplicationClass.getApplicationComponent(context))
                .build()
                .inject(this);

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_crypto_list_layout, container, false);

        recyclerView = root.findViewById(R.id.viewRecyclerView);
        toolbar = root.findViewById(R.id.viewToolbar);
        TextView toolbarTitle = toolbar.findViewById(R.id.viewToolbarTextView);
        toolbarTitle.setText(getString(R.string.crypto_test_app));
        adapter = new CryptoCurrenciesListAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrenciesList(new InternetConnectionJava().isConnectedToInternet(getActivity()));
    }

    @Override
    public void onPause() {
        presenter.setView(null);
        super.onPause();
    }

    @Override
    public void onStop() {
        presenter.setView(null);
        super.onStop();
    }

    @Override
    public void itemClicked(@NotNull CryptoCurrencyEntity currency, @NotNull List<WatchListEntity> watchList) {
        presenter.addCurrencyToWatchList(currency, watchList);
    }

    @Override
    public void setEntityList(@NotNull CryptoCurrencyAndWatchlistObject data) {
        adapter.setList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void noInternetConnectionInformation() {
        Toast.makeText(getActivity(), getString(R.string.there_is_no_internet_connection), Toast.LENGTH_SHORT).show();
    }
}
