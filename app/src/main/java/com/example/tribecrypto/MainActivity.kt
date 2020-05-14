package com.example.tribecrypto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tribecrypto.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class MainActivity : AppCompatActivity() {

//    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        toolbar = viewToolbar as Toolbar
//        setSupportActionBar(toolbar)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewViewPager.adapter = viewPagerAdapter
        viewTabLayout.setupWithViewPager(viewViewPager, true)
    }
}
