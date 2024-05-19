package com.example.mvvm.ui.main

sealed class State {
    object Loading : State()
    data class Success(val result: String) : State()
    object NoText : State()

}