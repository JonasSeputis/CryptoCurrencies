package com.example.tribecrypto.base

interface Presenter<T> {

    fun setView(view: T?)
}