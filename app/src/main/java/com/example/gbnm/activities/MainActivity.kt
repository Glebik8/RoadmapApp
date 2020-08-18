package com.example.gbnm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gbnm.R
import com.example.gbnm.adapters.NewsAdapter
import com.example.gbnm.data.News
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recommendationRecyclerView: RecyclerView
    private lateinit var recommendationRecyclerViewAdapter: NewsAdapter

    private lateinit var bottomRecyclerView: RecyclerView
    private lateinit var bottomRecyclerViewAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recommendationRecyclerView = best_recycler
        recommendationRecyclerViewAdapter = NewsAdapter(
            listOf(
                News("Основы Web разработки", "glebik8", "Kak je hochetsa asochku"),
                News("Красота React", "glebik8", "123"),
                News("Angular 9", "stepan", "mda")
            )
        ) {
            val intent = Intent(this, ContentActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
        bottomRecyclerViewAdapter = NewsAdapter(
            listOf(
                News("Хуки в React", "glebik8", "Kak je hochetsa asochku"),
                News("Концепты Vue.js", "glebik8", "123")
            )
        ) {
            val intent = Intent(this, ContentActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
        recommendationRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recommendationRecyclerViewAdapter
            isNestedScrollingEnabled = false
        }

        bottomRecyclerView = bottom_recycler
        bottomRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bottomRecyclerViewAdapter
            isNestedScrollingEnabled = false
        }


    }

}
