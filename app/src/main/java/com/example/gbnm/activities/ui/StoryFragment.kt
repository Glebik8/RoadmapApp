package com.example.gbnm.activities.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.example.gbnm.R
import com.example.gbnm.data.Menu
import com.example.gbnm.data.News
import com.example.gbnm.data.NoGodNo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.noties.markwon.Markwon
import io.noties.markwon.syntax.Prism4jSyntaxHighlight
import io.noties.markwon.syntax.Prism4jTheme
import io.noties.prism4j.GrammarLocator
import io.noties.prism4j.Prism4j
import kotlinx.android.synthetic.main.fragment_story.view.*

class StoryFragment : Fragment() {

    lateinit var news: News
    private var isShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        news = arguments!!.getParcelable("content")!!
        val view = inflater.inflate(R.layout.fragment_story, container, false)
        val content: TextView = view.story_text
        val menuFab: FloatingActionButton = view.menu_fab
        val bookmarksFab: FloatingActionButton = view.bookmarks_fab
        val shareFab: FloatingActionButton = view.share_fab
        val markwon = Markwon.create(context!!)
        markwon.setMarkdown(content, NoGodNo.MARKDOWN)
        shareFab.setOnClickListener {
            val share = Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                action = Intent.ACTION_SEND
                type = "image/*"
                putExtra(Intent.EXTRA_TEXT, "https://hrendium/news/${news.id}")
                putExtra(Intent.EXTRA_TITLE, "Share with your friends")
                }, null)
            startActivity(share)
        }
        val menu = Menu(
            menuFab,
            listOf(bookmarksFab, shareFab)
        )
        menuFab.setOnClickListener {
            if (!isShown) {
                menu.show()
            } else {
                menu.close()
            }
            isShown = !isShown
        }
        return view
    }

    companion object {
        fun newInstance(news: News) = StoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable("content", news)
            }
        }
    }

    private fun playAnimation(fab: FloatingActionButton, from: Float, to: Float, distance: Float) {
        val alpha = ObjectAnimator.ofFloat(fab, "alpha", from, to)
        val move = ObjectAnimator.ofFloat(fab, "translationY", distance)
        AnimatorSet().apply {
            playTogether(move, alpha)
            duration = 200
            start()
        }
    }

    private fun Menu.show() {
        var distance = -170f
        ObjectAnimator.ofFloat(mainButton, "rotation", 0f, 180f).apply {
            duration = 100
            start()
        }
        other.forEach {
            playAnimation(it, 0f, 1f, distance)
            distance -= 170f
        }
    }

    private fun Menu.close() {
        ObjectAnimator.ofFloat(mainButton, "rotation", 180f, 0f).apply {
            duration = 100
            start()
        }
        other.forEach {
            playAnimation(it, 1f, 0f, 0f)
        }
    }

}
