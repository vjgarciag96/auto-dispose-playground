package com.vjgarcia.autodisposableplayground.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.AutoDispose
import com.vjgarcia.autodisposableplayground.autodispose.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SecondsViewModelWithInheritance : BaseViewModel() {

    private val mutableSeconds = MutableLiveData<Long>()
    val seconds: LiveData<Long> = mutableSeconds
    private val mutableIsDisposed = MutableLiveData<Boolean>()
    val isDisposed: LiveData<Boolean> = mutableIsDisposed

    override fun start() {
        super.start()

        Observable.interval(1, TimeUnit.SECONDS)
            .doOnSubscribe { mutableIsDisposed.value = false }
            .doOnDispose {
                Log.d(LOG_TAG, "Subscription disposed auto magically")
                mutableIsDisposed.value = true
            }
            .`as`(AutoDispose.autoDisposable(this))
            .subscribe(mutableSeconds::postValue)
    }

    private companion object {
        const val LOG_TAG = "SecondsVM - Inheritance"
    }
}