package com.upineda.codingchallenge

sealed class TracksState {
    object Loading : TracksState()
    class Complete(val data: Results) : TracksState()
    class Error(val throwable: Throwable) : TracksState()
}