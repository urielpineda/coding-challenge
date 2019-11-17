package com.upineda.codingchallenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upineda.codingchallenge.TracksRepository
import com.upineda.codingchallenge.TracksState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TracksViewModel : ViewModel() {
    val tracksState = MutableLiveData<TracksState>()

    val tracksRepository = TracksRepository()

    var dateExited = ""

    init {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            tracksState.value = TracksState.Error(throwable)
        }) {
            tracksState.value = TracksState.Loading
            tracksState.value =
                TracksState.Complete(tracksRepository.getListOfTracks())
        }
    }
}