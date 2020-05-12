package com.example.tribecrypto.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T> : Presenter<T> {

    val subscriptions: CompositeDisposable = CompositeDisposable()

    private var view: T? = null

    fun hasView(): Boolean {
        return view != null
    }

    fun getView(): T? {
        return view
    }

    override fun setView(view: T) {
        this.view = view
        if (view == null) {
            subscriptions.clear()
        }
    }
}