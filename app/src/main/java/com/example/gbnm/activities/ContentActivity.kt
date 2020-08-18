package com.example.gbnm.activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.gbnm.R
import com.example.gbnm.adapters.SectionsPagerAdapter
import com.example.gbnm.data.News
import com.google.android.material.tabs.TabLayoutMediator


class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        val news = intent.getParcelableExtra<News>("id")!!
        val sectionsPagerAdapter = SectionsPagerAdapter(news, this)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.isUserInputEnabled = false
        val tabs = findViewById<TabLayout>(R.id.tabs)
        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = if (position == 1) "История" else "Roadmap"
        }.attach()
    }
}
