package com.example.mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.NoText)
    val state = _state.asStateFlow()

    fun onStartSearch(search: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            delay(5000)
            _state.value = State.Success(search)
        }
    }
}
