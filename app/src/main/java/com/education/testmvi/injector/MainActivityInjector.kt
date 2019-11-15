package com.education.testmvi.injector

import com.education.testmvi.domain.GetTextUseCase
import com.education.testmvi.domain.GetTextUseCaseImpl
import com.education.testmvi.presentation.MainActivity
import com.education.testmvi.presentation.MainPresenter

class MainActivityInjector : Injector<MainActivity> {

    private var getTextUseCase: GetTextUseCase = GetTextUseCaseImpl()
    private var presenter = MainPresenter(getTextUseCase)

    override fun clean() {

    }

    override fun inject(activity: MainActivity) {
        activity.presenter = presenter
    }

}