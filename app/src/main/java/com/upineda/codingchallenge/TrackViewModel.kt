package com.upineda.codingchallenge

import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.ViewModel

class TrackViewModel : ViewModel() {
    var detailTitle = "Invalid track"
    lateinit var img : BitmapDrawable
}