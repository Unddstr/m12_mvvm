package com.example.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.NoText)
    val state = _state.asStateFlow()

    var resultSearch: MutableLiveData<String> = MutableLiveData("Результат поиска ->")

    fun onStartSearch() {
        viewModelScope.launch {
            _state.value = State.Loading
            delay(5000)
            _state.value = State.Success
        }
    }
}
