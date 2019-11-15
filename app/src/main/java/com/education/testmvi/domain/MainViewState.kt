package com.education.testmvi.domain

sealed class MainViewState {
    object LoadingState : MainViewState()
    data class DataState(val text: String) : MainViewState()
    data class ErrorState(val error: Throwable) : MainViewState()
}