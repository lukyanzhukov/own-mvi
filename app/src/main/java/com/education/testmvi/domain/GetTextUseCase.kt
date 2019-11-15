package com.education.testmvi.domain

import com.education.testmvi.data.TextRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


interface GetTextUseCase {
    fun getText(): Observable<MainViewState>
}

class GetTextUseCaseImpl : GetTextUseCase {
    override fun getText(): Observable<MainViewState> {
        return TextRepository.loadText()
            .map<MainViewState> { MainViewState.DataState(it) }
            .subscribeOn(Schedulers.io())
            .startWith(MainViewState.LoadingState)
            .onErrorReturn { MainViewState.ErrorState(it) }
    }
}