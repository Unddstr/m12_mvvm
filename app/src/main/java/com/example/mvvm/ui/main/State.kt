package com.example.mvvm.ui.main

sealed class State {
    object Loading : State()
    object Success : State()
    object NoText : State()

/*    data class Error(
        val searchError: String?) :State()*/
}