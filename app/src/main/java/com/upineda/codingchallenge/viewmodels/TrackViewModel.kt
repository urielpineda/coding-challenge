package com.upineda.codingchallenge.viewmodels

import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.ViewModel

class TrackViewModel : ViewModel() {
    var detailTitle = ""
    lateinit var img: BitmapDrawable
}