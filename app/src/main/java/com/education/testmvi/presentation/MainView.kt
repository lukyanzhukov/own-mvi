package com.education.testmvi.presentation

import com.education.testmvi.domain.MainViewState
import io.reactivex.Observable

interface MainView {

    fun showTextIntent(): Observable<Unit>

    fun render(state: MainViewState)
}