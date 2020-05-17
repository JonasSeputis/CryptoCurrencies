package com.example.tribecrypto.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tribecrypto.crypto_list.CryptoListFragment;
import com.example.tribecrypto.crypto_watch_list.CryptoWatchlistFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new CryptoWatchlistFragment();
        }
        return new CryptoListFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
