package com.upineda.codingchallenge

/** States when loading the list of tracks */
sealed class TracksState {
    object Loading : TracksState()
    class Complete(val data: Results) : TracksState()
    class Error(val throwable: Throwable) : TracksState()
}