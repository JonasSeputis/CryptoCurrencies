package com.example.tribecrypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.tribecrypto.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = viewToolbar as Toolbar
        setSupportActionBar(toolbar)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewViewPager.adapter = viewPagerAdapter
    }
}
