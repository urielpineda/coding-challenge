package com.upineda.codingchallenge

import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

    var details = "No details found"

    fun setDetails(track: Track) {
        var price = track.price
        if(track.price.toFloatOrNull() != null)
            price += " " + track.currency

        val text = "Name: " + track.trackName + " \n" +
                "Price: " + price + " \n" +
                "Genre: " + track.genre + " \n \n" +
                track.longDescription

        details = text
    }
}