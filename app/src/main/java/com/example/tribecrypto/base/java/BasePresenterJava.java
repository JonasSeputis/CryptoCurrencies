package com.example.tribecrypto.base.java;

import com.example.tribecrypto.base.Presenter;

import org.jetbrains.annotations.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenterJava<T> implements Presenter<T> {

    public CompositeDisposable subscriptions = new CompositeDisposable();

    private T view = null;

    public Boolean hasView() {
        return view != null;
    }

    public T getView() {
        return view;
    }

    @Override
    public void setView(@Nullable T view) {
        this.view = view;
        if(view == null) {
            subscriptions.clear();
        }
    }
}
