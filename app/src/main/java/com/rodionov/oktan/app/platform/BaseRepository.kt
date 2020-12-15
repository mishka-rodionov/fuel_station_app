package com.rodionov.oktan.app.platform

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class BaseRepository {

//    fun <T> launchObservable(observable: Observable<T>, onNext: (T) -> Unit) {
//        val dispose = observable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        {
//                            onNext.invoke(it)
//                        }, {
//
//                }, {
//
//                }
//                )
//        dispose.
//    }

}