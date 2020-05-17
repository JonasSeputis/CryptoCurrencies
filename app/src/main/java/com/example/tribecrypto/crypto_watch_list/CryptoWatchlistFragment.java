package com.example.tribecrypto.crypto_watch_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tribecrypto.R;
import com.example.tribecrypto.adapter.CryptoWatchlistAdapter;
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.java.ApplicationClass;

import java.util.List;

import javax.inject.Inject;

public class CryptoWatchlistFragment extends Fragment implements CryptoWatchlistView {

    @Inject
    CryptoWatchlistPresenter presenter;

    private CryptoWatchlistAdapter adapter;
    private TextView toolbarTextView;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        DaggerCryptoWatchlistComponent.builder()
                .applicationComponent(ApplicationClass.getApplicationComponent(context))
                .build()
                .inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_crypto_list_layout, container, false);

        adapter = new CryptoWatchlistAdapter(getActivity());
        toolbarTextView = root.findViewById(R.id.viewToolbarTextView);
        toolbarTextView.setText(getString(R.string.watchlist));
        recyclerView = root.findViewById(R.id.viewRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadInfo();
    }

    @Override
    public void onPause() {
        presenter.setView(null);
        super.onPause();
    }

    @Override
    public void provideCurrencyList(List<CryptoCurrencyEntity> data) {
        adapter.setList(data);
        adapter.notifyDataSetChanged();
    }
}
