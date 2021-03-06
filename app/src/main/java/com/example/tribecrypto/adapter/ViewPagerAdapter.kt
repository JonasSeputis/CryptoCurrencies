package com.example.tribecrypto.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tribecrypto.crypto_list.CryptoListFragment
import com.example.tribecrypto.crypto_watch_list.CryptoWatchlistFragment

class ViewPagerAdapter constructor(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CryptoListFragment()
            1 -> CryptoWatchlistFragment()
            else -> CryptoListFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}