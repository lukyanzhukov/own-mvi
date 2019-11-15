package com.education.testmvi.presentation

import com.education.testmvi.domain.GetTextUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainPresenter(private val getTextUseCase: GetTextUseCase) {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: MainView

    fun bind(view: MainView) {
        this.view = view
        compositeDisposable.add(mainState())
    }

    fun unbind() {
        compositeDisposable.dispose()
    }

    private fun mainState(): Disposable {
        Timber.d("mainState")
        return view.showTextIntent()
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap { getTextUseCase.getText() }
            .doOnNext {
                Timber.d("Received new state: $it")
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view.render(it) },
                {
                    Timber.d(it)
                }
            )
    }
}