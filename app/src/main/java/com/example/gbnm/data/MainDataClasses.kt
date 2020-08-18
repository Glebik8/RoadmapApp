package com.example.gbnm.data

import android.os.Parcelable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val title: String,
    val author: String,
    val content: String,
    val url: String = "https://andreasbm.github.io/web-skills",
    val id: Int = title.hashCode(),
    val time: Int = (1..15).random()
) : Parcelable


data class Menu(
    val mainButton: FloatingActionButton,
    val other: List<FloatingActionButton>
)
