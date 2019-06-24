package com.vjgarcia.autodisposableplayground.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uber.autodispose.AutoDispose.autoDisposable
import com.vjgarcia.autodisposableplayground.autodispose.ViewModelScope
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SecondsViewModelWithComposition(private val viewModelScope: ViewModelScope) : ViewModel() {

    private val mutableSeconds = MutableLiveData<Long>()
    val seconds: LiveData<Long> = mutableSeconds
    private val mutableIsDisposed = MutableLiveData<Boolean>()
    val isDisposed: LiveData<Boolean> = mutableIsDisposed

    fun start() {
        viewModelScope.start()
        Observable.interval(1, TimeUnit.SECONDS)
            .doOnSubscribe { mutableIsDisposed.value = false }
            .doOnDispose {
                Log.d(LOG_TAG, "Subscription disposed auto magically")
                mutableIsDisposed.value = true
            }
            .`as`(autoDisposable(viewModelScope))
            .subscribe(mutableSeconds::postValue)
    }

    fun stop() {
        viewModelScope.end()
    }

    private companion object {
        const val LOG_TAG = "SecondsVM - Composition"
    }
}