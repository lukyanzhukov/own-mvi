package com.education.testmvi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.education.testmvi.App
import com.education.testmvi.R
import com.education.testmvi.domain.MainViewState
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    lateinit var presenter: MainPresenter

    override fun showTextIntent(): Observable<Unit> = textButton.clicks()

    override fun render(state: MainViewState) {
        when (state) {
            is MainViewState.LoadingState -> renderLoadingState()
            is MainViewState.DataState -> renderDataState(state)
            is MainViewState.ErrorState -> renderErrorState(state)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.injectMainActivity(this)
        presenter.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    private fun renderLoadingState() {
        loadingIndicator.visibility = View.VISIBLE
        textView.visibility = View.GONE
    }

    private fun renderDataState(dataState: MainViewState.DataState) {
        loadingIndicator.visibility = View.GONE
        textView.apply {
            visibility = View.VISIBLE
            text = dataState.text
        }
    }

    private fun renderErrorState(errorState: MainViewState.ErrorState) {
        loadingIndicator.visibility = View.GONE
        textView.visibility = View.GONE
        Toast.makeText(this, "error ${errorState.error}", Toast.LENGTH_LONG).show()
    }
}
