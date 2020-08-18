package com.example.gbnm.adapters

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gbnm.R
import com.example.gbnm.data.News
import kotlinx.android.synthetic.main.card_layout.view.*

class NewsAdapter(private val newsList: List<News>, val callback: (News) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private lateinit var resourses: Resources

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsHolder {
        resourses = p0.resources
        return NewsHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.card_layout, p0, false)
        )
    }

    override fun onBindViewHolder(p0: NewsHolder, p1: Int) {
        with(p0) {
            author.text = newsList[p1].author
            title.text = newsList[p1].title
            image.setImageBitmap(BitmapFactory.decodeResource(resourses, generate(newsList[p1].title)))
        }
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.title_text as TextView
        val author = view.author_text as TextView
        val image = view.image_content as ImageView

        init {
            view.setOnClickListener {
                callback(newsList[adapterPosition])
            }
        }
    }


    private fun generate(value: String): Int {
        return when {
            value.contains("React") || value.contains("Web") -> R.drawable.react
            value.contains("Angular") -> R.drawable.angular
            value.contains("Vue.js") -> R.drawable.vue
            else -> R.drawable.react
        }
    }
}

