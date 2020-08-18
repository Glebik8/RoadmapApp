package com.example.gbnm.activities.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.gbnm.R
import com.example.gbnm.data.News
import kotlinx.android.synthetic.main.fragment_roadmap.view.*


class RoadmapFragment : Fragment() {

    lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        news = arguments!!.getParcelable("content")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_roadmap, container, false)
        val webView: WebView = view.webview
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(news.url)
        return view
    }

    companion object {
        fun newInstance(news: News) = RoadmapFragment().apply {
            arguments = Bundle().apply {
                putParcelable("content", news)
            }
        }
    }
}
