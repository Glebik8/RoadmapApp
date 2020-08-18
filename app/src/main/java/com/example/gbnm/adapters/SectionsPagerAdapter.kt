package com.example.gbnm.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gbnm.activities.ui.RoadmapFragment
import com.example.gbnm.activities.ui.StoryFragment
import com.example.gbnm.data.News


class SectionsPagerAdapter(private val news: News, activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) StoryFragment.newInstance(news) else RoadmapFragment.newInstance(news)
    }

    override fun getItemCount(): Int = 2
}

