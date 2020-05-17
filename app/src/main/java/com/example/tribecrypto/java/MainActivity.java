package com.example.tribecrypto.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.tribecrypto.R;
import com.example.tribecrypto.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewViewPager);
        TabLayout viewTabLayout = findViewById(R.id.viewTabLayout);
        viewPager.setAdapter(adapter);
        viewTabLayout.setupWithViewPager(viewPager, true);
    }
}
