package com.education.testmvi.injector

interface Injector<T> {
    fun inject(context: T)
    fun clean()
}