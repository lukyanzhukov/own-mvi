package com.education.testmvi.data

import io.reactivex.Observable
import java.util.*


object TextRepository {

    fun loadText(): Observable<String> = Observable.just(getRandomMessage())

    private fun getRandomMessage(): String {
        val messages = listOf("Hello World", "Hola Mundo", "Hallo Welt", "Bonjour le monde")
        return messages[Random().nextInt(messages.size)]
    }
}