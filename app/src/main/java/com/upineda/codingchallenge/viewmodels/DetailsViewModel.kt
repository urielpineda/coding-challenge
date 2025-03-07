package com.upineda.codingchallenge.viewmodels

import androidx.lifecycle.ViewModel
import com.upineda.codingchallenge.Track

class DetailsViewModel : ViewModel() {

    var details = ""

    fun setDetails(track: Track) {
        var price = track.price
        if (track.price.toFloatOrNull() != null)
            price += " " + track.currency

        /**
        *  Name:
        *  Price:
        *  Genre:
        *
        *
        *  <long description>
         */
        val text = "Name: " + track.trackName + " \n" +
                "Price: " + price + " \n" +
                "Genre: " + track.genre + " \n \n" +
                track.longDescription

        details = text
    }
}